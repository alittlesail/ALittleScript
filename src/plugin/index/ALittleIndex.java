package plugin.index;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.util.PathUtil;
import com.intellij.util.io.URLUtil;
import org.jetbrains.annotations.NotNull;
import plugin.alittle.PsiHelper;
import plugin.component.StdLibraryProvider;
import plugin.psi.*;
import plugin.reference.ALittleReferenceUtil;

import java.io.File;
import java.util.*;

public class ALittleIndex {
    // 对应的项目
    Project mProject;

    // 保存关键的元素对象，用于快速语法树解析
    protected Map<PsiFile, Map<PsiElement, List<ALittleReferenceUtil.GuessTypeInfo>>> mGuessTypeMap;
    protected Map<PsiFile, Map<ALittleClassDec, ALittleClassData>> mClassDataMap;
    protected Map<PsiFile, Map<ALittleStructDec, ALittleStructData>> mStructDataMap;
    protected Map<PsiFile, Map<ALittleEnumDec, ALittleEnumData>> mEnumDataMap;

    // 按命名域来划分
    protected Map<String, Map<ALittleNamespaceNameDec, ALittleAccessData>> mAllDataMap;
    // 根据开放权限来划分
    protected Map<String, ALittleAccessData> mGlobalAccessMap;
    protected Map<String, ALittleAccessData> mNamespaceAccessMap;
    protected Map<PsiFile, ALittleAccessData> mFileAccessMap;

    // 已加载的文件列表
    boolean mReloading = false;         // 是否正在加载
    boolean mReloaded = false;          // 是否加载完成
    boolean mIsRefreshed = false;       // 是否刷新过

    public ALittleIndex(Project project) {
        mProject = project;
        mAllDataMap = new HashMap<>();
        mGlobalAccessMap = new HashMap<>();
        mNamespaceAccessMap = new HashMap<>();
        mFileAccessMap = new HashMap<>();

        mGuessTypeMap = new HashMap<>();
        mClassDataMap = new HashMap<>();
        mStructDataMap = new HashMap<>();
        mEnumDataMap = new HashMap<>();
    }

    private void loadDir(PsiManager psi_mgr, VirtualFile root) {
        if (root.isDirectory()) {
            VirtualFile[] files = root.getChildren();
            if (files != null) {
                for (VirtualFile file : files) {
                    loadDir(psi_mgr, file);
                }
            }
        } else {
            PsiFile psi_file = psi_mgr.findFile(root);
            if (psi_file instanceof ALittleFile) {
                List<ALittleNamespaceDec> namespaceDecList = new ArrayList<>();
                for(PsiElement child = psi_file.getFirstChild(); child != null; child = child.getNextSibling()) {
                    if (child instanceof ALittleNamespaceDec) {
                        namespaceDecList.add((ALittleNamespaceDec)child);
                    }
                }
                for (ALittleNamespaceDec namespaceDec : namespaceDecList) {

                    ALittleNamespaceNameDec namespaceNameDec = namespaceDec.getNamespaceNameDec();
                    if (namespaceNameDec == null) continue;

                    addNamespaceName(namespaceNameDec);
                }
            }
        }
    }

    public boolean isLoaded() {
        return mReloaded;
    }

    public boolean isLoading() {
        return mReloading;
    }

    public void reload() {
        mAllDataMap = new HashMap<>();
        mGlobalAccessMap = new HashMap<>();
        mNamespaceAccessMap = new HashMap<>();
        mFileAccessMap = new HashMap<>();

        mGuessTypeMap = new HashMap<>();
        mClassDataMap = new HashMap<>();
        mStructDataMap = new HashMap<>();
        mEnumDataMap = new HashMap<>();

        mReloading = true;

        PsiManager psi_mgr = PsiManager.getInstance(mProject);
        VirtualFile[] roots = ProjectRootManager.getInstance(mProject).getContentRoots();
        for (VirtualFile root : roots) {
            loadDir(psi_mgr, root);
        }


        if (!StdLibraryProvider.isPluginSelf(mProject)) {
            try {
                // 适配代码
                String jarPath = PathUtil.getJarPathForClass(StdLibraryProvider.class);
                VirtualFile dir;
                if (jarPath.endsWith(".jar"))
                    dir = VfsUtil.findFileByURL(URLUtil.getJarEntryURL(new File(jarPath), "std"));
                else
                    dir = VfsUtil.findFileByIoFile(new File(jarPath + "/std"), true);

                if (dir != null) {
                    loadDir(psi_mgr, dir);
                }
            } catch (Exception e) {
            }
        }

        mReloading = false;
        mReloaded = true;
    }

    public void refresh() {
        if (mIsRefreshed) return;
        mIsRefreshed = true;
        reload();
    }

    private void addClassData(@NotNull ALittleClassDec classDec) {
        Map<ALittleClassDec, ALittleClassData> map = mClassDataMap.get(classDec.getContainingFile());
        if (map == null) {
            map = new HashMap<>();
            mClassDataMap.put(classDec.getContainingFile(), map);
        }
        ALittleClassData classData = new ALittleClassData();
        map.put(classDec, classData);

        for (PsiElement child = classDec.getFirstChild(); child != null; child = child.getNextSibling()) {
            classData.addALittleClassChildDec(child);
        }
    }

    public ALittleClassData getClassData(@NotNull ALittleClassDec dec) {
        Map<ALittleClassDec, ALittleClassData> map = mClassDataMap.get(dec.getContainingFile());
        if (map == null) return null;
        return map.get(dec);
    }

    private void addStructData(@NotNull ALittleStructDec structDec) {
        Map<ALittleStructDec, ALittleStructData> map = mStructDataMap.get(structDec.getContainingFile());
        if (map == null) {
            map = new HashMap<>();
            mStructDataMap.put(structDec.getContainingFile(), map);
        }
        ALittleStructData structData = new ALittleStructData();
        map.put(structDec, structData);

        for (PsiElement child = structDec.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof ALittleStructVarDec) {
                structData.addVarDec((ALittleStructVarDec)child);
            }
        }
    }

    public ALittleStructData getStructData(@NotNull ALittleStructDec dec) {
        Map<ALittleStructDec, ALittleStructData> map = mStructDataMap.get(dec.getContainingFile());
        if (map == null) return null;
        return map.get(dec);
    }

    private void addEnumData(@NotNull ALittleEnumDec enumDec) {
        Map<ALittleEnumDec, ALittleEnumData> map = mEnumDataMap.get(enumDec.getContainingFile());
        if (map == null) {
            map = new HashMap<>();
            mEnumDataMap.put(enumDec.getContainingFile(), map);
        }
        ALittleEnumData enumData = new ALittleEnumData();
        map.put(enumDec, enumData);

        for (PsiElement child = enumDec.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof ALittleEnumVarDec) {
                enumData.addVarDec((ALittleEnumVarDec)child);
            }
        }
    }

    public ALittleEnumData getEnumData(@NotNull ALittleEnumDec dec) {
        Map<ALittleEnumDec, ALittleEnumData> map = mEnumDataMap.get(dec.getContainingFile());
        if (map == null) return null;
        return map.get(dec);
    }

    protected void addNamespaceName(@NotNull ALittleNamespaceNameDec element) {
        // 清除标记
        mGuessTypeMap.remove(element.getContainingFile());
        mClassDataMap.remove(element.getContainingFile());
        mStructDataMap.remove(element.getContainingFile());
        mEnumDataMap.remove(element.getContainingFile());

        String namespaceName = element.getText();

        // 处理mNamespaceMap
        Map<ALittleNamespaceNameDec, ALittleAccessData> allDataMap = mAllDataMap.get(namespaceName);
        if (allDataMap == null) {
            allDataMap = new HashMap<>();
            mAllDataMap.put(namespaceName, allDataMap);
        }

        ALittleAccessData allAccessData = new ALittleAccessData();
        allDataMap.put(element, allAccessData);

        ALittleAccessData globalAccessData = mGlobalAccessMap.get(namespaceName);
        if (globalAccessData == null) {
            globalAccessData = new ALittleAccessData();
            mGlobalAccessMap.put(namespaceName, globalAccessData);
        }

        ALittleAccessData namespaceAccessData = mNamespaceAccessMap.get(namespaceName);
        if (namespaceAccessData == null) {
            namespaceAccessData = new ALittleAccessData();
            mNamespaceAccessMap.put(namespaceName, namespaceAccessData);
        }

        ALittleAccessData fileAccessData = mFileAccessMap.get(element.getContainingFile());
        if (fileAccessData == null) {
            fileAccessData = new ALittleAccessData();
            mFileAccessMap.put(element.getContainingFile(), fileAccessData);
        }

        ALittleNamespaceDec namespaceDec = (ALittleNamespaceDec)element.getParent();
        for(PsiElement child = namespaceDec.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof ALittleClassDec) {
                ALittleClassDec dec = (ALittleClassDec)child;
                ALittleClassNameDec nameDec = dec.getClassNameDec();
                if (nameDec == null) continue;

                addClassData(dec);
                allAccessData.addALittleNameDec(nameDec);
                PsiHelper.ClassAccessType accessType = PsiHelper.calcAccessType(dec.getAccessModifier());
                if (accessType == PsiHelper.ClassAccessType.PUBLIC) {
                    globalAccessData.addALittleNameDec(nameDec);
                } else if (accessType == PsiHelper.ClassAccessType.PROTECTED) {
                    namespaceAccessData.addALittleNameDec(nameDec);
                } else if (accessType == PsiHelper.ClassAccessType.PRIVATE) {
                    fileAccessData.addALittleNameDec(nameDec);
                }
            } else if (child instanceof ALittleEnumDec) {
                ALittleEnumDec dec = (ALittleEnumDec)child;
                ALittleEnumNameDec nameDec = dec.getEnumNameDec();
                if (nameDec == null) continue;

                addEnumData(dec);
                allAccessData.addALittleNameDec(nameDec);
                PsiHelper.ClassAccessType accessType = PsiHelper.calcAccessType(dec.getAccessModifier());
                if (accessType == PsiHelper.ClassAccessType.PUBLIC) {
                    globalAccessData.addALittleNameDec(nameDec);
                } else if (accessType == PsiHelper.ClassAccessType.PROTECTED) {
                    namespaceAccessData.addALittleNameDec(nameDec);
                } else if (accessType == PsiHelper.ClassAccessType.PRIVATE) {
                    fileAccessData.addALittleNameDec(nameDec);
                }
            } else if (child instanceof ALittleStructDec) {
                ALittleStructDec dec = (ALittleStructDec)child;
                ALittleStructNameDec nameDec = dec.getStructNameDec();
                if (nameDec == null) continue;

                addStructData(dec);
                allAccessData.addALittleNameDec(nameDec);
                PsiHelper.ClassAccessType accessType = PsiHelper.calcAccessType(dec.getAccessModifier());
                if (accessType == PsiHelper.ClassAccessType.PUBLIC) {
                    globalAccessData.addALittleNameDec(nameDec);
                } else if (accessType == PsiHelper.ClassAccessType.PROTECTED) {
                    namespaceAccessData.addALittleNameDec(nameDec);
                } else if (accessType == PsiHelper.ClassAccessType.PRIVATE) {
                    fileAccessData.addALittleNameDec(nameDec);
                }
            } else if (child instanceof ALittleGlobalMethodDec) {
                ALittleGlobalMethodDec dec = (ALittleGlobalMethodDec)child;
                ALittleMethodNameDec nameDec = dec.getMethodNameDec();
                if (nameDec == null) continue;

                allAccessData.addALittleNameDec(nameDec);
                PsiHelper.ClassAccessType accessType = PsiHelper.calcAccessType(dec.getAccessModifier());
                if (accessType == PsiHelper.ClassAccessType.PUBLIC) {
                    globalAccessData.addALittleNameDec(nameDec);
                } else if (accessType == PsiHelper.ClassAccessType.PROTECTED) {
                    namespaceAccessData.addALittleNameDec(nameDec);
                } else if (accessType == PsiHelper.ClassAccessType.PRIVATE) {
                    fileAccessData.addALittleNameDec(nameDec);
                }
            } else if (child instanceof ALittleInstanceDec) {
                ALittleInstanceDec dec = (ALittleInstanceDec) child;
                PsiHelper.ClassAccessType accessType = PsiHelper.calcAccessType(dec.getAccessModifier());

                List<ALittleVarAssignDec> varAssignDecList = dec.getVarAssignExpr().getVarAssignDecList();
                for (ALittleVarAssignDec varAssignDec : varAssignDecList) {
                    ALittleVarAssignNameDec nameDec = varAssignDec.getVarAssignNameDec();

                    allAccessData.addALittleNameDec(nameDec);
                    if (accessType == PsiHelper.ClassAccessType.PUBLIC) {
                        globalAccessData.addALittleNameDec(nameDec);
                    } else if (accessType == PsiHelper.ClassAccessType.PROTECTED) {
                        namespaceAccessData.addALittleNameDec(nameDec);
                    } else if (accessType == PsiHelper.ClassAccessType.PRIVATE) {
                        fileAccessData.addALittleNameDec(nameDec);
                    }
                }
            }
        }
    }

    protected void removeNamespaceName(ALittleNamespaceNameDec element) {
        // 清除标记
        mGuessTypeMap.remove(element.getContainingFile());
        mClassDataMap.remove(element.getContainingFile());
        mStructDataMap.remove(element.getContainingFile());
        mEnumDataMap.remove(element.getContainingFile());

        String namespaceName = element.getText();

        Map<ALittleNamespaceNameDec, ALittleAccessData> allDataMap = mAllDataMap.get(namespaceName);
        if (allDataMap == null) return;
        ALittleAccessData allAccessData = allDataMap.get(element);
        if (allAccessData == null) return;

        allDataMap.remove(element);
        if (allDataMap.isEmpty()) {
            mAllDataMap.remove(namespaceName);
        }

        ALittleAccessData globalAccessData = mGlobalAccessMap.get(namespaceName);
        ALittleAccessData namespaceAccessData = mNamespaceAccessMap.get(namespaceName);
        ALittleAccessData fileAccessData = mFileAccessMap.get(element.getContainingFile());

        for (Map.Entry<PsiHelper.PsiElementType, Map<String, Set<PsiElement>>> entry : allAccessData.elementMap.entrySet()) {
            for (Map.Entry<String, Set<PsiElement>> elementEntry : entry.getValue().entrySet()) {
                for (PsiElement nameDec : elementEntry.getValue()) {
                    if (globalAccessData != null) globalAccessData.removeALittleNameDec(nameDec);
                    if (namespaceAccessData != null) namespaceAccessData.removeALittleNameDec(nameDec);
                    if (fileAccessData != null) fileAccessData.removeALittleNameDec(nameDec);
                }
            }
        }
    }
}