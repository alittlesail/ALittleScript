package plugin.generate;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.FileIndexFacade;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.PathUtil;
import com.intellij.util.io.URLUtil;
import org.jetbrains.annotations.NotNull;
import plugin.alittle.PsiHelper;
import plugin.component.StdLibraryProvider;
import plugin.alittle.FileHelper;
import plugin.psi.*;
import plugin.reference.ALittleReferenceInterface;
import plugin.reference.ALittleReferenceUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ALittleGenerateLua {
    private String mNamespaceName = "";

    private boolean mOpenRawSet = false;
    private int mRawsetUseCount = 0;

    private void copyStdLibrary(String moduleBasePath) throws Exception {
        File file = new File(moduleBasePath + "/std");
        if (file.exists()) return;
        if (!file.mkdirs())
            throw new Exception("文件夹创建失败:" + file.getPath());

        // 适配代码
        String jarPath = PathUtil.getJarPathForClass(StdLibraryProvider.class);
        VirtualFile dir;
        if (jarPath.endsWith(".jar"))
            dir = VfsUtil.findFileByURL(URLUtil.getJarEntryURL(new File(jarPath), "adapter/Lua"));
        else
            dir = VfsUtil.findFileByIoFile(new File(jarPath +"/adapter/Lua"), true);

        if (dir != null) {
            VirtualFile[] fileList = dir.getChildren();
            if (fileList != null) {
                for (VirtualFile virtualFile : fileList) {
                    FileOutputStream fileOut = new FileOutputStream(new File(moduleBasePath + "/std/" + virtualFile.getName()));
                    fileOut.write(virtualFile.contentsToByteArray());
                    fileOut.close();
                }
            }
        }
    }

    private void checkErrorElement(PsiElement element, boolean fullCheck) throws ALittleReferenceUtil.ALittleReferenceException {
        for(PsiElement child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof PsiErrorElement) {
                throw new ALittleReferenceUtil.ALittleReferenceException(child, ((PsiErrorElement) child).getErrorDescription());
            }

            if (fullCheck) {
                // 检查错误，给元素上色
                PsiReference ref = element.getReference();
                if (ref instanceof ALittleReferenceInterface) {
                    ((ALittleReferenceInterface) ref).checkError();
                }
            }

            checkErrorElement(child, fullCheck);
        }
    }

    public void GenerateLua(ALittleFile alittleFile, boolean fullCheck) throws Exception {
        // 获取语法错误
        try {
            checkErrorElement(alittleFile, fullCheck);
        } catch (ALittleReferenceUtil.ALittleReferenceException e) {
            throw new Exception(e.getElement().getContainingFile().getName() + "有语法错误:" + e.getError());
        }

        List<String> mEnumList = new ArrayList<>();
        List<String> mClassList = new ArrayList<>();

        ALittleNamespaceDec namespaceDec = PsiHelper.getNamespaceDec(alittleFile);
        if (namespaceDec == null) throw new Exception("没有定义命名域 namespace");

        // 如果命名域有register标记，那么就不需要生成
        if (namespaceDec.getRegisterModifier() != null) {
            return;
        }

        // 生成代码
        String content = GenerateNamespace(namespaceDec);

        // 保存到文件
        FileIndexFacade facade = FileIndexFacade.getInstance(alittleFile.getProject());
        Module module = facade.getModuleForFile(alittleFile.getVirtualFile());
        if (module == null) {
            return;
        }

        String alittleRelPath = FileHelper.calcALittleRelPath(module, alittleFile.getVirtualFile());
        FileHelper.writeFile(FileHelper.calcScriptPath(module) + alittleRelPath + "lua", content);

        // 复制标准库
        copyStdLibrary(FileHelper.calcScriptPath(module));
    }

    @NotNull
    private String GenerateBindStat(ALittleBindStat bindStat) throws Exception {
        List<ALittleValueStat> valueStatList = bindStat.getValueStatList();

        String content = "ALittle.Bind(";
        if (PsiHelper.getNamespaceName(bindStat.getContainingFile()).equals("ALittle"))
            content = "Bind(";
        List<String> paramList = new ArrayList<>();
        for (ALittleValueStat valueStat : valueStatList) {
            paramList.add(GenerateValueStat(valueStat));
        }
        content += String.join(", ", paramList);
        content += ")";
        return content;
    }

    @NotNull
    private String GenerateOpNewListStat(ALittleOpNewListStat opNewList) throws Exception {
        List<ALittleValueStat> valueStatList = opNewList.getValueStatList();

        String content = "{";
        List<String> paramList = new ArrayList<>();
        for (ALittleValueStat valueStat : valueStatList) {
            paramList.add(GenerateValueStat(valueStat));
        }
        content += String.join(", ", paramList);
        content += "}";
        return content;
    }

    @NotNull
    private String GenerateOpNewStat(ALittleOpNewStat opNewStat) throws Exception {
        // 如果是通用类型
        ALittleGenericType genericType = opNewStat.getGenericType();
        if (genericType != null) {
            // 如果是Map，那么直接返回{}
            ALittleGenericMapType mapType = genericType.getGenericMapType();
            if (mapType != null) return "{}";

            // 如果是List，那么直接返回{}
            ALittleGenericListType listType = genericType.getGenericListType();
            if (listType != null) return "{}";

            ALittleGenericFunctorType functorType = genericType.getGenericFunctorType();
            if (functorType != null) {
                throw new Exception("Functor不能使用new来创建");
            }
        }

        // 自定义类型
        ALittleCustomType customType = opNewStat.getCustomType();
        if (customType != null) {
            ALittleReferenceUtil.GuessTypeInfo guessType = customType.guessType();
            // 如果是结构体名，那么就当表来处理
            if (guessType.type == ALittleReferenceUtil.GuessType.GT_STRUCT) {
                return "{}";
            // 如果是类名
            } else if (guessType.type == ALittleReferenceUtil.GuessType.GT_CLASS) {
                // 如果是类名
                String content = "";
                ALittleNamespaceNameDec namespaceNameDec = customType.getNamespaceNameDec();
                if (namespaceNameDec != null)
                    content = namespaceNameDec.getIdContent().getText() + ".";
                content += customType.getIdContent().getText() + "(";

                List<String> paramList = new ArrayList<>();
                List<ALittleValueStat> valueStatList = opNewStat.getValueStatList();
                for (ALittleValueStat valueStat : valueStatList) {
                    paramList.add(GenerateValueStat(valueStat));
                }
                content += String.join(", ", paramList);

                content += ")";
                return content;
            }
        }

        throw new Exception("new 未知类型");
    }

    @NotNull
    private String GenerateOp8Suffix(ALittleOp8Suffix suffix) throws Exception {
        String opString = suffix.getOp8().getText();
        if (opString.equals("||")) {
            opString = "or";
        }

        String valueFactorResult = null;
        if (suffix.getValueFactorStat() != null) {
            valueFactorResult = GenerateValueFactorStat(suffix.getValueFactorStat());
        } else if (suffix.getOp2Value() != null) {
            valueFactorResult = GenerateOp2Value(suffix.getOp2Value());
        }

        List<String> suffixContentList = new ArrayList<>();
        List<ALittleOp8SuffixEe> suffixEeList = suffix.getOp8SuffixEeList();
        for (ALittleOp8SuffixEe suffixEe : suffixEeList) {
            String suffixEeResult = GenerateOp8SuffixEe(suffixEe);
            suffixContentList.add(suffixEeResult);
        }
        String content = opString + " " + valueFactorResult;
        if (!suffixContentList.isEmpty()) content += " " + String.join(" ", suffixContentList);
        return content;
    }

    @NotNull
    private String GenerateOp8SuffixEe(ALittleOp8SuffixEe suffix) throws Exception {
        if (suffix.getOp3Suffix() != null) {
            return GenerateOp3Suffix(suffix.getOp3Suffix());
        } else if (suffix.getOp4Suffix() != null) {
            return GenerateOp4Suffix(suffix.getOp4Suffix());
        } else if (suffix.getOp5Suffix() != null) {
            return GenerateOp5Suffix(suffix.getOp5Suffix());
        } else if (suffix.getOp6Suffix() != null) {
            return GenerateOp6Suffix(suffix.getOp6Suffix());
        } else if (suffix.getOp7Suffix() != null) {
            return GenerateOp7Suffix(suffix.getOp7Suffix());
        } else {
            throw new Exception("GenerateOp8SuffixEe出现未知的表达式");
        }
    }

    @NotNull
    private String GenerateOp8SuffixEx(ALittleOp8SuffixEx suffix) throws Exception {
        if (suffix.getOp8Suffix() != null) {
            return GenerateOp8Suffix(suffix.getOp8Suffix());
        } else {
            throw new Exception("GenerateOp8SuffixEx出现未知的表达式");
        }
    }

    @NotNull
    private String GenerateOp8Stat(ALittleOp8Stat op8Stat) throws Exception {
        String valueFactorResult = GenerateValueFactorStat(op8Stat.getValueFactorStat());

        ALittleOp8Suffix suffix = op8Stat.getOp8Suffix();
        String suffixResult = GenerateOp8Suffix(suffix);

        List<String> suffixContentList = new ArrayList<>();
        List<ALittleOp8SuffixEx> suffixExList = op8Stat.getOp8SuffixExList();
        for (ALittleOp8SuffixEx suffixEx : suffixExList) {
            suffixContentList.add(GenerateOp8SuffixEx(suffixEx));
        }
        String content = valueFactorResult + " " + suffixResult;
        if (!suffixContentList.isEmpty()) content += " " + String.join(" ", suffixContentList);
        return content;
    }

    @NotNull
    private String GenerateOp7Suffix(ALittleOp7Suffix suffix) throws Exception {
        String opString = suffix.getOp7().getText();
        if (opString.equals("&&")) {
            opString = "and";
        }

        String valueFactorResult = null;
        if (suffix.getValueFactorStat() != null) {
            valueFactorResult = GenerateValueFactorStat(suffix.getValueFactorStat());
        } else if (suffix.getOp2Value() != null) {
            valueFactorResult = GenerateOp2Value(suffix.getOp2Value());
        }

        List<String> suffixContentList = new ArrayList<>();
        List<ALittleOp7SuffixEe> suffixEeList = suffix.getOp7SuffixEeList();
        for (ALittleOp7SuffixEe suffixEe : suffixEeList) {
            suffixContentList.add(GenerateOp7SuffixEe(suffixEe));
        }
        String content = opString + " " + valueFactorResult;
        if (!suffixContentList.isEmpty()) content += " " + String.join(" ", suffixContentList);
        return content;
    }

    @NotNull
    private String GenerateOp7SuffixEe(ALittleOp7SuffixEe suffix) throws Exception {
        if (suffix.getOp3Suffix() != null) {
            return GenerateOp3Suffix(suffix.getOp3Suffix());
        } else if (suffix.getOp4Suffix() != null) {
            return GenerateOp4Suffix(suffix.getOp4Suffix());
        } else if (suffix.getOp5Suffix() != null) {
            return GenerateOp5Suffix(suffix.getOp5Suffix());
        } else if (suffix.getOp6Suffix() != null) {
            return GenerateOp6Suffix(suffix.getOp6Suffix());
        } else {
            throw new Exception("GenerateOp7SuffixEe出现未知的表达式");
        }
    }

    @NotNull
    private String GenerateOp7SuffixEx(ALittleOp7SuffixEx suffix) throws Exception {
        if (suffix.getOp7Suffix() != null) {
            return GenerateOp7Suffix(suffix.getOp7Suffix());
        } else if (suffix.getOp8Suffix() != null) {
            return GenerateOp8Suffix(suffix.getOp8Suffix());
        } else {
            throw new Exception("GenerateOp7SuffixEx出现未知的表达式");
        }
    }

    @NotNull
    private String GenerateOp7Stat(ALittleOp7Stat op7Stat) throws Exception {
        String valueFactorResult = GenerateValueFactorStat(op7Stat.getValueFactorStat());

        ALittleOp7Suffix suffix = op7Stat.getOp7Suffix();
        String suffixResult = GenerateOp7Suffix(suffix);

        List<String> suffixContentList = new ArrayList<>();
        List<ALittleOp7SuffixEx> suffixExList = op7Stat.getOp7SuffixExList();
        for (ALittleOp7SuffixEx suffixEx : suffixExList) {
            suffixContentList.add(GenerateOp7SuffixEx(suffixEx));
        }
        String content = valueFactorResult + " " + suffixResult;
        if (!suffixContentList.isEmpty()) content += " " + String.join(" ", suffixContentList);
        return content;
    }

    @NotNull
    private String GenerateOp6Suffix(ALittleOp6Suffix suffix) throws Exception {
        String opString = suffix.getOp6().getText();
        if (opString.equals("!=")) {
            opString = "~=";
        }

        String valueFactorResult = null;
        if (suffix.getValueFactorStat() != null) {
            valueFactorResult = GenerateValueFactorStat(suffix.getValueFactorStat());
        } else if (suffix.getOp2Value() != null) {
            valueFactorResult = GenerateOp2Value(suffix.getOp2Value());
        }

        List<String> suffixContentList = new ArrayList<>();
        List<ALittleOp6SuffixEe> suffixEeList = suffix.getOp6SuffixEeList();
        for (ALittleOp6SuffixEe suffixEe : suffixEeList) {
            suffixContentList.add(GenerateOp6SuffixEe(suffixEe));
        }
        String content = opString + " " + valueFactorResult;
        if (!suffixContentList.isEmpty()) content += " " + String.join(" ", suffixContentList);
        return content;
    }

    @NotNull
    private String GenerateOp6SuffixEe(ALittleOp6SuffixEe suffix) throws Exception {
        if (suffix.getOp3Suffix() != null) {
            return GenerateOp3Suffix(suffix.getOp3Suffix());
        } else if (suffix.getOp4Suffix() != null) {
            return GenerateOp4Suffix(suffix.getOp4Suffix());
        } else if (suffix.getOp5Suffix() != null) {
            return GenerateOp5Suffix(suffix.getOp5Suffix());
        } else {
            throw new Exception("GenerateOp6SuffixEe出现未知的表达式");
        }
    }

    @NotNull
    private String GenerateOp6SuffixEx(ALittleOp6SuffixEx suffix) throws Exception {
        if (suffix.getOp6Suffix() != null) {
            return GenerateOp6Suffix(suffix.getOp6Suffix());
        } else if (suffix.getOp7Suffix() != null) {
            return GenerateOp7Suffix(suffix.getOp7Suffix());
        } else if (suffix.getOp8Suffix() != null) {
            return GenerateOp8Suffix(suffix.getOp8Suffix());
        } else {
            throw new Exception("GenerateOp6SuffixEx出现未知的表达式");
        }
    }

    @NotNull
    private String GenerateOp6Stat(ALittleOp6Stat op6Stat) throws Exception {
        String valueFactorResult = GenerateValueFactorStat(op6Stat.getValueFactorStat());

        ALittleOp6Suffix suffix = op6Stat.getOp6Suffix();
        String suffixResult = GenerateOp6Suffix(suffix);

        List<String> suffixContentList = new ArrayList<>();
        List<ALittleOp6SuffixEx> suffixExList = op6Stat.getOp6SuffixExList();
        for (ALittleOp6SuffixEx suffixEx : suffixExList) {
            suffixContentList.add(GenerateOp6SuffixEx(suffixEx));
        }
        String content = valueFactorResult + " " + suffixResult;
        if (!suffixContentList.isEmpty()) content += " " + String.join(" ", suffixContentList);
        return content;
    }

    @NotNull
    private String GenerateOp5Suffix(ALittleOp5Suffix suffix) throws Exception {
        String opString = suffix.getOp5().getText();

        String valueFactorResult = null;
        if (suffix.getValueFactorStat() != null) {
            valueFactorResult = GenerateValueFactorStat(suffix.getValueFactorStat());
        } else if (suffix.getOp2Value() != null) {
            valueFactorResult = GenerateOp2Value(suffix.getOp2Value());
        }

        List<String> suffixContentList = new ArrayList<>();
        List<ALittleOp5SuffixEe> suffixEeList = suffix.getOp5SuffixEeList();
        for (ALittleOp5SuffixEe suffixEe : suffixEeList) {
            suffixContentList.add(GenerateOp5SuffixEe(suffixEe));
        }
        String content = opString + " " + valueFactorResult;
        if (!suffixContentList.isEmpty()) content += " " + String.join(" ", suffixContentList);
        return content;
    }

    @NotNull
    private String GenerateOp5SuffixEe(ALittleOp5SuffixEe suffix) throws Exception {
        if (suffix.getOp3Suffix() != null) {
            return GenerateOp3Suffix(suffix.getOp3Suffix());
        } else if (suffix.getOp4Suffix() != null) {
            return GenerateOp4Suffix(suffix.getOp4Suffix());
        } else {
            throw new Exception("GenerateOp5SuffixEe出现未知的表达式");
        }
    }

    @NotNull
    private String GenerateOp5SuffixEx(ALittleOp5SuffixEx suffix) throws Exception {
        if (suffix.getOp5Suffix() != null) {
            return GenerateOp5Suffix(suffix.getOp5Suffix());
        } else if (suffix.getOp6Suffix() != null) {
            return GenerateOp6Suffix(suffix.getOp6Suffix());
        } else if (suffix.getOp7Suffix() != null) {
            return GenerateOp7Suffix(suffix.getOp7Suffix());
        } else if (suffix.getOp8Suffix() != null) {
            return GenerateOp8Suffix(suffix.getOp8Suffix());
        } else {
            throw new Exception("GenerateOp5SuffixEx出现未知的表达式");
        }
    }

    @NotNull
    private String GenerateOp5Stat(ALittleOp5Stat op5Stat) throws Exception {
        String valueFactorResult = GenerateValueFactorStat(op5Stat.getValueFactorStat());

        ALittleOp5Suffix suffix = op5Stat.getOp5Suffix();
        String suffixResult = GenerateOp5Suffix(suffix);

        List<String> suffixContentList = new ArrayList<>();
        List<ALittleOp5SuffixEx> suffixExList = op5Stat.getOp5SuffixExList();
        for (ALittleOp5SuffixEx suffixEx : suffixExList) {
            suffixContentList.add(GenerateOp5SuffixEx(suffixEx));
        }
        String content = valueFactorResult + " " + suffixResult;
        if (!suffixContentList.isEmpty()) content += " " + String.join(" ", suffixContentList);
        return content;
    }

    @NotNull
    private String GenerateOp4Suffix(ALittleOp4Suffix suffix) throws Exception {
        String opString = suffix.getOp4().getText();

        String valueFactorResult = null;
        if (suffix.getValueFactorStat() != null) {
            valueFactorResult = GenerateValueFactorStat(suffix.getValueFactorStat());
        } else if (suffix.getOp2Value() != null) {
            valueFactorResult = GenerateOp2Value(suffix.getOp2Value());
        }

        List<String> suffixContentList = new ArrayList<>();
        List<ALittleOp4SuffixEe> suffixEeList = suffix.getOp4SuffixEeList();
        for (ALittleOp4SuffixEe suffixEe : suffixEeList) {
            suffixContentList.add(GenerateOp4SuffixEe(suffixEe));
        }
        String content = opString + " " + valueFactorResult;
        if (!suffixContentList.isEmpty()) content += " " + String.join(" ", suffixContentList);
        return content;
    }

    @NotNull
    private String GenerateOp4SuffixEe(ALittleOp4SuffixEe suffix) throws Exception {
        if (suffix.getOp3Suffix() != null) {
            return GenerateOp3Suffix(suffix.getOp3Suffix());
        } else {
            throw new Exception("GenerateOp4SuffixEe出现未知的表达式");
        }
    }

    @NotNull
    private String GenerateOp4SuffixEx(ALittleOp4SuffixEx suffix) throws Exception {
        if (suffix.getOp4Suffix() != null) {
            return GenerateOp4Suffix(suffix.getOp4Suffix());
        } else if (suffix.getOp5Suffix() != null) {
            return GenerateOp5Suffix(suffix.getOp5Suffix());
        } else if (suffix.getOp6Suffix() != null) {
            return GenerateOp6Suffix(suffix.getOp6Suffix());
        } else if (suffix.getOp7Suffix() != null) {
            return GenerateOp7Suffix(suffix.getOp7Suffix());
        } else if (suffix.getOp8Suffix() != null) {
            return GenerateOp8Suffix(suffix.getOp8Suffix());
        } else {
            throw new Exception("GenerateOp4SuffixEx出现未知的表达式");
        }
    }

    @NotNull
    private String GenerateOp4Stat(ALittleOp4Stat op4Stat) throws Exception {
        String valueFactorResult = GenerateValueFactorStat(op4Stat.getValueFactorStat());

        ALittleOp4Suffix suffix = op4Stat.getOp4Suffix();
        String suffixResult = GenerateOp4Suffix(suffix);

        List<String> suffixContentList = new ArrayList<>();
        List<ALittleOp4SuffixEx> suffixExList = op4Stat.getOp4SuffixExList();
        for (ALittleOp4SuffixEx suffixEx : suffixExList) {
            suffixContentList.add(GenerateOp4SuffixEx(suffixEx));
        }
        String content = valueFactorResult + " " + suffixResult;
        if (!suffixContentList.isEmpty()) content += " " + String.join(" ", suffixContentList);
        return content;
    }

    @NotNull
    private String GenerateOp3Suffix(ALittleOp3Suffix suffix) throws Exception {
        String opString = suffix.getOp3().getText();

        String valueResult;
        if (suffix.getValueFactorStat() != null) {
            valueResult = GenerateValueFactorStat(suffix.getValueFactorStat());
        } else if (suffix.getOp2Value() != null) {
            valueResult = GenerateOp2Value(suffix.getOp2Value());
        } else {
            throw new Exception("GenerateOp3Suffix出现未知的表达式");
        }

        return opString + " " + valueResult;
    }

    @NotNull
    private String GenerateOp3SuffixEx(ALittleOp3SuffixEx suffix) throws Exception {
        if (suffix.getOp3Suffix() != null) {
            return GenerateOp3Suffix(suffix.getOp3Suffix());
        } else if (suffix.getOp4Suffix() != null) {
            return GenerateOp4Suffix(suffix.getOp4Suffix());
        } else if (suffix.getOp5Suffix() != null) {
            return GenerateOp5Suffix(suffix.getOp5Suffix());
        } else if (suffix.getOp6Suffix() != null) {
            return GenerateOp6Suffix(suffix.getOp6Suffix());
        } else if (suffix.getOp7Suffix() != null) {
            return GenerateOp7Suffix(suffix.getOp7Suffix());
        } else if (suffix.getOp8Suffix() != null) {
            return GenerateOp8Suffix(suffix.getOp8Suffix());
        } else {
            throw new Exception("GenerateOp3SuffixEx出现未知的表达式");
        }
    }

    @NotNull
    private String GenerateOp3Stat(ALittleOp3Stat op3Stat) throws Exception {
        String valueFactorResult = GenerateValueFactorStat(op3Stat.getValueFactorStat());

        ALittleOp3Suffix suffix = op3Stat.getOp3Suffix();
        String suffixResult = GenerateOp3Suffix(suffix);

        List<String> suffixContentList = new ArrayList<>();
        List<ALittleOp3SuffixEx> suffixExList = op3Stat.getOp3SuffixExList();
        for (ALittleOp3SuffixEx suffixEx : suffixExList) {
            suffixContentList.add(GenerateOp3SuffixEx(suffixEx));
        }
        String content = valueFactorResult + " " + suffixResult;
        if (!suffixContentList.isEmpty()) content += " " + String.join(" ", suffixContentList);
        return content;
    }

    @NotNull
    private String GenerateOp2SuffixEx(ALittleOp2SuffixEx suffix) throws Exception {
        if (suffix.getOp3Suffix() != null) {
            return GenerateOp3Suffix(suffix.getOp3Suffix());
        } else if (suffix.getOp4Suffix() != null) {
            return GenerateOp4Suffix(suffix.getOp4Suffix());
        } else if (suffix.getOp5Suffix() != null) {
            return GenerateOp5Suffix(suffix.getOp5Suffix());
        } else if (suffix.getOp6Suffix() != null) {
            return GenerateOp6Suffix(suffix.getOp6Suffix());
        } else if (suffix.getOp7Suffix() != null) {
            return GenerateOp7Suffix(suffix.getOp7Suffix());
        } else if (suffix.getOp8Suffix() != null) {
            return GenerateOp8Suffix(suffix.getOp8Suffix());
        } else {
            throw new Exception("GenerateOp2SuffixEx出现未知的表达式");
        }
    }

    @NotNull
    private String GenerateOp2Value(ALittleOp2Value op2Value) throws Exception {
        String content = "";

        ALittleValueFactorStat valueFactor = op2Value.getValueFactorStat();
        if (valueFactor == null) {
            throw new Exception("GenerateOp2Stat单目运算没有操作对象");
        }

        String valueStatResult = GenerateValueFactorStat(valueFactor);
        String opString = op2Value.getOp2().getText();
        if (opString.equals("!")) {
            content += "not " + valueStatResult;
        } else if (opString.equals("-")) {
            content += "-" + valueStatResult;
        } else {
            throw new Exception("GenerateOp2Stat出现未知类型");
        }

        return content;
    }

    @NotNull
    private String GenerateOp2Stat(ALittleOp2Stat op2Stat) throws Exception {
        String content = GenerateOp2Value(op2Stat.getOp2Value());

        List<String> suffixContentList = new ArrayList<>();
        List<ALittleOp2SuffixEx> suffixExList = op2Stat.getOp2SuffixExList();
        for (ALittleOp2SuffixEx suffixEx : suffixExList) {
            suffixContentList.add(GenerateOp2SuffixEx(suffixEx));
        }
        if (!suffixContentList.isEmpty()) content += " " + String.join(" ", suffixContentList);
        return content;
    }

    @NotNull
    private String GenerateValueStat(ALittleValueStat rootStat) throws Exception {
        ALittleValueFactorStat valueFactor = rootStat.getValueFactorStat();
        if (valueFactor != null) return GenerateValueFactorStat(valueFactor);

        ALittleOp2Stat op2Stat = rootStat.getOp2Stat();
        if (op2Stat != null) return GenerateOp2Stat(op2Stat);

        ALittleOp3Stat op3Stat = rootStat.getOp3Stat();
        if (op3Stat != null) return GenerateOp3Stat(op3Stat);

        ALittleOp4Stat op4Stat = rootStat.getOp4Stat();
        if (op4Stat != null) return GenerateOp4Stat(op4Stat);

        ALittleOp5Stat op5Stat = rootStat.getOp5Stat();
        if (op5Stat != null) return GenerateOp5Stat(op5Stat);

        ALittleOp6Stat op6Stat = rootStat.getOp6Stat();
        if (op6Stat != null) return GenerateOp6Stat(op6Stat);

        ALittleOp7Stat op7Stat = rootStat.getOp7Stat();
        if (op7Stat != null) return GenerateOp7Stat(op7Stat);

        ALittleOp8Stat op8Stat = rootStat.getOp8Stat();
        if (op8Stat != null) return GenerateOp8Stat(op8Stat);

        ALittleOpNewStat opNewStat = rootStat.getOpNewStat();
        if (opNewStat != null) return GenerateOpNewStat(opNewStat);

        ALittleOpNewListStat opNewListStat = rootStat.getOpNewListStat();
        if (opNewListStat != null) return GenerateOpNewListStat(opNewListStat);

        ALittleBindStat bindStat = rootStat.getBindStat();
        if (bindStat != null) return GenerateBindStat(bindStat);

        return "";
    }

    @NotNull
    private String GenerateValueFactorStat(ALittleValueFactorStat valueFactor) throws Exception {
        ALittleConstValue constValue = valueFactor.getConstValue();
        if (constValue != null) {
            return GenerateConstValue(constValue);
        }

        ALittleReflectValue reflectValue = valueFactor.getReflectValue();
        if (reflectValue != null) {
            return GenerateReflectValue(reflectValue);
        }

        ALittlePropertyValue propValue = valueFactor.getPropertyValue();
        if (propValue != null) {
            return GeneratePropertyValue(propValue);
        }

        ALittleWrapValueStat wrapValueStat = valueFactor.getWrapValueStat();
        if (wrapValueStat != null) {
            String result = GenerateValueStat(wrapValueStat.getValueStat());
            return "(" + result + ")";
        }

        throw new Exception("GenerateValueFactor出现未知类型");
    }

    @NotNull
    private String GenerateConstValue(ALittleConstValue constValue) throws Exception {
        String content = "";
        String constValueString = constValue.getText();
        if (constValueString.equals("null"))
            content += "nil";
        else
            content += constValueString;
        return content;
    }

    @NotNull
    static public String GenerateReflectValue(ALittleReflectValue reflectValue) throws Exception {
        String content = "";
        ALittleCustomType customType = reflectValue.getCustomType();
        if (customType == null) return content;
        ALittleReferenceUtil.GuessTypeInfo guessType = customType.guessType();

        // 把获取到的对象转为Json

        return "\"" + content.replace("\"", "\\\"") + "\"";
    }

    @NotNull
    private String GeneratePropertyValue(ALittlePropertyValue propValue) throws Exception {
        try {
            StringBuilder content = new StringBuilder();

            // 用来标记第一个变量是不是lua命名域
            boolean isLuaNamespace = false;

            // 获取开头的属性信息
            ALittlePropertyValueFirstType firstType = propValue.getPropertyValueFirstType();
            ALittlePropertyValueCustomType customType = firstType.getPropertyValueCustomType();
            ALittlePropertyValueThisType thisType = firstType.getPropertyValueThisType();
            ALittlePropertyValueCastType castType = firstType.getPropertyValueCastType();

            ALittleReferenceUtil.GuessTypeInfo customGuessType = firstType.guessType();
            if (customType != null) {
                if (customGuessType.type == ALittleReferenceUtil.GuessType.GT_NAMESPACE_NAME && customGuessType.value.equals("lua"))
                    isLuaNamespace = true;

                // 如果是lua命名域，那么就忽略
                if (!isLuaNamespace)
                    content.append(customType.getText());
                // 如果是this，那么就变为self
            } else if (thisType != null) {
                content.append("self");
            } else if (castType != null) {
                ALittleValueFactorStat valueFactorStat = castType.getValueFactorStat();
                if (valueFactorStat == null) throw new Exception("cast没有填写转换对象");
                content.append(GenerateValueFactorStat(valueFactorStat));
            }

            // 后面跟着后缀属性
            List<ALittlePropertyValueSuffix> suffixList = propValue.getPropertyValueSuffixList();
            for (int index = 0; index < suffixList.size(); ++index) {
                // 获取当前后缀
                ALittlePropertyValueSuffix suffix = suffixList.get(index);
                // 获取上一个后缀
                ALittlePropertyValueSuffix preSuffix = null;
                if (index - 1 >= 0) preSuffix = suffixList.get(index - 1);
                // 获取下一个后缀
                ALittlePropertyValueSuffix nextSuffix = null;
                if (index + 1 < suffixList.size()) nextSuffix = suffixList.get(index + 1);

                // 如果当前是
                ALittlePropertyValueDotId dotId = suffix.getPropertyValueDotId();
                if (dotId != null) {
                    // 获取类型
                    ALittleReferenceUtil.GuessTypeInfo guess = dotId.guessType();

                    if (!isLuaNamespace) {
                        String split = ".";
                        // 如果是函数名
                        if (guess.type == ALittleReferenceUtil.GuessType.GT_FUNCTOR) {
                            // 1. 是成员函数
                            // 2. 使用的是调用
                            // 3. 前一个后缀是类实例对象
                            // 那么就要改成使用语法糖
                            if (guess.element instanceof ALittleClassMethodDec) {
                                if (nextSuffix != null && nextSuffix.getPropertyValueMethodCall() != null) {
                                    // 获取前一个后缀的类型
                                    ALittleReferenceUtil.GuessTypeInfo preGuess = customGuessType;
                                    if (preSuffix != null) {
                                        preGuess = preSuffix.guessType();
                                    }

                                    // 只要不是类名，那么肯定就是类实例对象，就是用语法糖
                                    if (preGuess.type != ALittleReferenceUtil.GuessType.GT_CLASS_NAME)
                                        split = ":";
                                }
                                // setter和getter需要特殊处理
                            } else if (guess.element instanceof ALittleClassSetterDec
                                    || guess.element instanceof ALittleClassGetterDec) {
                                if (nextSuffix != null && nextSuffix.getPropertyValueMethodCall() != null) {
                                    ALittleReferenceUtil.GuessTypeInfo preGuess = customGuessType;
                                    if (preSuffix != null) {
                                        preGuess = preSuffix.guessType();
                                    }

                                    // 如果前一个后缀是类名，那么那么就需要获取setter或者getter来获取
                                    if (preGuess.type == ALittleReferenceUtil.GuessType.GT_CLASS_NAME) {
                                        // 如果是getter，那么一定是一个参数，比如ClassName.disabled(self)
                                        // 如果是setter，那么一定是两个参数，比如ClassName.width(self, 100)
                                        if (nextSuffix.getPropertyValueMethodCall().getValueStatList().size() == 1)
                                            split = ".__getter.";
                                        else
                                            split = ".__setter.";
                                    }
                                }
                            }
                        }
                        content.append(split);
                    }

                    if (dotId.getPropertyValueDotIdName() == null) {
                        throw new Exception("点后面没有内容");
                    }

                    String nameContent = dotId.getPropertyValueDotIdName().getText();
                    // 因为lua中自带的string模块名和关键字string一样，所以把lua自动的改成String（大些开头）
                    // 然后再翻译的时候，把String改成string
                    if (isLuaNamespace && nameContent.equals("String"))
                        nameContent = "string";
                    content.append(nameContent);

                    // 置为false，表示不是命名域
                    isLuaNamespace = false;
                    continue;
                }

                ALittlePropertyValueBracketValue bracketValue = suffix.getPropertyValueBracketValue();
                if (bracketValue != null) {
                    ALittleValueStat valueStat = bracketValue.getValueStat();
                    if (valueStat != null) {
                        content.append("[").append(GenerateValueStat(valueStat)).append("]");
                    }
                    continue;
                }

                ALittlePropertyValueMethodCall methodCall = suffix.getPropertyValueMethodCall();
                if (methodCall != null) {
                    List<ALittleValueStat> valueStatList = methodCall.getValueStatList();
                    List<String> paramList = new ArrayList<>();
                    for (ALittleValueStat valueStat : valueStatList) {
                        paramList.add(GenerateValueStat(valueStat));
                    }
                    content.append("(").append(String.join(", ", paramList)).append(")");
                    continue;
                }

                throw new Exception("GeneratePropertyValue出现未知类型");
            }

            return content.toString();
        } catch (ALittleReferenceUtil.ALittleReferenceException e) {
            throw new Exception(e.getError());
        }
    }

    @NotNull
    private String GeneratePropertyValueExpr(ALittlePropertyValueExpr root, String preTab) throws Exception {
        return preTab + GeneratePropertyValue(root.getPropertyValue()) + "\n";
    }

    @NotNull
    private String GenerateOp1Expr(ALittleOp1Expr root, String preTab) throws Exception {
        ALittleValueStat valueStat = root.getValueStat();
        if (valueStat == null) {
            throw new Exception("GenerateOp1Expr 没有操作值:" + root.getText());
        }
        ALittleOp1 op1 = root.getOp1();

        String valueStatResult = GenerateValueStat(valueStat);

        String op1String = op1.getText();
        if (op1String.equals("++"))
            return preTab + valueStatResult + " = " + valueStatResult + " + 1\n";

        if (op1String.equals("--"))
            return preTab + valueStatResult + " = " + valueStatResult + " - 1\n";

        throw new Exception("GenerateOp1Expr未知类型:" + op1String);
    }

    @NotNull
    private String GenerateVarAssignExpr(ALittleVarAssignExpr root, String preTab, String preString) throws Exception {
        List<ALittleVarAssignDec> pairDecList = root.getVarAssignDecList();
        if (pairDecList.isEmpty()) {
            throw new Exception("局部变量没有变量名:" + root.getText());
        }

        String content = preTab + preString;

        List<String> nameList = new ArrayList<>();
        for (ALittleVarAssignDec pairDec : pairDecList) {
            nameList.add(pairDec.getVarAssignNameDec().getIdContent().getText());
        }
        content += String.join(", ", nameList);

        ALittleValueStat valueStat = root.getValueStat();
        if (valueStat == null)
            return content + "\n";

        return content + " = " + GenerateValueStat(valueStat) + "\n";
    }

    @NotNull
    private String GenerateOpAssignExpr(ALittleOpAssignExpr root, String preTab) throws Exception {
        List<ALittlePropertyValue> propValueList = root.getPropertyValueList();
        List<String> contentList = new ArrayList<>();
        for (ALittlePropertyValue propValue : propValueList) {
            contentList.add(GeneratePropertyValue(propValue));
        }

        String propValueResult = String.join(", ", contentList);

        ALittleOpAssign opAssign = root.getOpAssign();
        ALittleValueStat valueStat = root.getValueStat();
        if (opAssign == null || valueStat == null)
            return preTab + propValueResult + "\n";

        String valueStatResult = GenerateValueStat(valueStat);

        if (opAssign.getText().equals("=")) {
            // 这里做优化
            // 把 self._attr = value 优化为  rawset(self, "_attr", value)
            if (mOpenRawSet && propValueList.size() == 1) {
                ALittlePropertyValue propValue = propValueList.get(0);
                ALittlePropertyValueThisType thisType = propValue.getPropertyValueFirstType().getPropertyValueThisType();
                if (thisType != null && propValue.getPropertyValueSuffixList().size() == 1) {
                    ALittlePropertyValueSuffix suffix = propValue.getPropertyValueSuffixList().get(0);
                    if (suffix.getPropertyValueDotId() != null) {
                        ALittlePropertyValueDotId dotId = suffix.getPropertyValueDotId();
                        if (dotId != null && dotId.getPropertyValueDotIdName() != null) {
                            String attrName = dotId.getPropertyValueDotIdName().getText();
                            ALittleReferenceUtil.GuessTypeInfo thisGuessType = thisType.guessType();
                            if (thisGuessType.type == ALittleReferenceUtil.GuessType.GT_CLASS) {
                                List<PsiElement> varNameList = new ArrayList<>();
                                PsiHelper.findClassAttrList((ALittleClassDec) thisGuessType.element
                                        , PsiHelper.sAccessPrivateAndProtectedAndPublic
                                        , PsiHelper.ClassAttrType.VAR
                                        , attrName
                                        , varNameList, 100);
                                if (!varNameList.isEmpty()) {
                                    ++mRawsetUseCount;
                                    return preTab + "___rawset(self, \"" + attrName + "\", " + valueStatResult + ")\n";
                                }
                            }
                        }
                    }
                }
            }

            return preTab + propValueResult + " = " + valueStatResult + "\n";
        }

        String opAssignString = opAssign.getText();

        // 如果出现多个前缀赋值，那么只能是=号
        if (contentList.size() > 1)
            throw new Exception("等号左边出现多个值的时候，只能使用=赋值符号:" + root.getText());

        String content = "";
        switch (opAssignString) {
            case "+=":
            case "-=":
            case "*=":
            case "/=":
            case "%=":
                String opString = opAssignString.substring(0, 1);
                content = preTab + propValueResult + " = " + propValueResult + " " + opString + " (" + valueStatResult + ")\n";
                break;
            default:
                throw new Exception("未知的赋值操作类型:" + opAssignString);
        }
        return content;
    }

    @NotNull
    private String GenerateElseExpr(ALittleElseExpr root, String preTab) throws Exception {
        StringBuilder content = new StringBuilder(preTab);
        content.append("else\n");
        List<ALittleAllExpr> allExprList = root.getAllExprList();
        for (ALittleAllExpr allExpr : allExprList) {
            content.append(GenerateAllExpr(allExpr, preTab + "\t"));
        }
        return content.toString();
    }

    @NotNull
    private String GenerateElseIfExpr(ALittleElseIfExpr root, String preTab) throws Exception {
        ALittleValueStat valueStat = root.getValueStat();
        if (valueStat == null) {
            throw new Exception("elseif (?) elseif没有条件值:" + root.getText());
        }
        String valueStatResult = GenerateValueStat(valueStat);

        StringBuilder content = new StringBuilder(preTab);
        content.append("elseif")
                .append(" ")
                .append(valueStatResult)
                .append(" then\n");

        List<ALittleAllExpr> allExprList = root.getAllExprList();
        for (ALittleAllExpr allExpr : allExprList) {
            content.append(GenerateAllExpr(allExpr, preTab + "\t"));
        }
        return content.toString();
    }

    @NotNull
    private String GenerateIfExpr(ALittleIfExpr root, String preTab) throws Exception {
        ALittleValueStat valueStat = root.getValueStat();
        if (valueStat == null) {
            throw new Exception("if (?) if没有条件值:" + root.getText());
        }
        String valueStatResult = GenerateValueStat(valueStat);

        StringBuilder content = new StringBuilder(preTab);
        content.append("if")
                .append(" ")
                .append(valueStatResult)
                .append(" then\n");

        List<ALittleAllExpr> allExprList = root.getAllExprList();
        for (ALittleAllExpr allExpr : allExprList) {
            content.append(GenerateAllExpr(allExpr, preTab + "\t"));
        }

        List<ALittleElseIfExpr> elseIfExprList = root.getElseIfExprList();
        for (ALittleElseIfExpr elseIfExpr : elseIfExprList) {
            String result = GenerateElseIfExpr(elseIfExpr, preTab);
            if (result == null) return null;
            content.append(result);
        }

        ALittleElseExpr elseExpr = root.getElseExpr();
        if (elseExpr != null) {
            content.append(GenerateElseExpr(elseExpr, preTab));
        }
        content.append(preTab).append("end\n");
        return content.toString();
    }

    @NotNull
    private String GenerateForExpr(ALittleForExpr root, String preTab) throws Exception {
        ALittleForStepCondition forStepCondition = root.getForStepCondition();
        ALittleForInCondition forInCondition = root.getForInCondition();

        StringBuilder content = new StringBuilder(preTab);
        if (forStepCondition != null) {
            ALittleForStartStat forStartStat = forStepCondition.getForStartStat();

            ALittleValueStat startValueStat = forStartStat.getValueStat();
            if (startValueStat == null) {
                throw new Exception("for 没有初始表达式:" + root.getText());
            }
            String startValueStatResult = GenerateValueStat(startValueStat);

            ALittleVarAssignNameDec nameDec = forStartStat.getForPairDec().getVarAssignNameDec();
            if (nameDec == null) {
                throw new Exception("for 初始表达式没有变量名:" + root.getText());
            }
            String startVarName = nameDec.getText();

            content.append("for ")
                    .append(startVarName)
                    .append(" = ")
                    .append(startValueStatResult)
                    .append(", ");

            ALittleForEndStat forEndStat = forStepCondition.getForEndStat();
            if (forEndStat == null) {
                throw new Exception("for 没有结束表达式:" + root.getText());
            }

            ALittleValueStat endValueStat = forEndStat.getValueStat();
            content.append(GenerateValueStat(endValueStat));

            ALittleForStepStat forStepStat = forStepCondition.getForStepStat();
            if (forStepStat == null) {
                throw new Exception("for 没有步长表达式");
            }
            ALittleValueStat stepValueStat = forStepStat.getValueStat();
            content.append(", ").append(GenerateValueStat(stepValueStat));

            content.append(" do\n");
        } else if (forInCondition != null) {
            ALittleValueStat valueStat = forInCondition.getValueStat();
            if (valueStat == null) {
                throw new Exception("for in 没有遍历的对象:" + root.getText());
            }

            String valueStatResult = GenerateValueStat(valueStat);

            List<ALittleForPairDec> pairList = forInCondition.getForPairDecList();
            List<String> pairStringList = new ArrayList<>();
            for (ALittleForPairDec pair : pairList) {
                ALittleVarAssignNameDec nameDec = pair.getVarAssignNameDec();
                if (nameDec == null)
                    throw new Exception("for in 没有变量名");
                pairStringList.add(nameDec.getText());
            }

            String pairType = ALittleReferenceUtil.CalcPairsType(valueStat);

            // 如果foreach的参数数量不为2，那么就不用pairType
            if (pairType.isEmpty()) {
                content.append("for ")
                        .append(String.join(", ", pairStringList))
                        .append(" in ")
                        .append(valueStatResult)
                        .append(" do\n");
            } else {
                content.append("for ")
                        .append(String.join(", ", pairStringList))
                        .append(" in ")
                        .append(pairType)
                        .append("(")
                        .append(valueStatResult)
                        .append(") do\n");
            }
        } else {
            throw new Exception("for(?) 无效的for语句:" + root.getText());
        }

        List<ALittleAllExpr> allExprList = root.getAllExprList();
        for (ALittleAllExpr allExpr : allExprList) {
            content.append(GenerateAllExpr(allExpr, preTab + "\t"));
        }

        content.append(preTab).append("end\n");
        return content.toString();
    }

    @NotNull
    private String GenerateWhileExpr(ALittleWhileExpr root, String preTab) throws Exception {
        ALittleValueStat valueStat = root.getValueStat();
        if (valueStat == null) {
            throw new Exception("while (?) { ... } while中没有条件值");
        }
        String valueStatResult = GenerateValueStat(valueStat);

        StringBuilder content = new StringBuilder(preTab + "while " + valueStatResult + " do\n");
        List<ALittleAllExpr> allExprList = root.getAllExprList();
        for (ALittleAllExpr allExpr : allExprList) {
            String result = GenerateAllExpr(allExpr, preTab + "\t");
            if (result == null) return null;
            content.append(result);
        }
        content.append(preTab).append("end\n");
        return content.toString();
    }

    @NotNull
    private String GenerateDoWhileExpr(ALittleDoWhileExpr rootExpr, String preTab) throws Exception {
        ALittleValueStat valueStat = rootExpr.getValueStat();
        if (valueStat == null) {
            throw new Exception("do { ... } while(?) while中没有条件值");
        }
        String valueStatResult = GenerateValueStat(valueStat);

        StringBuilder content = new StringBuilder(preTab + "repeat\n");
        List<ALittleAllExpr> allExprList = rootExpr.getAllExprList();
        for (ALittleAllExpr allExpr : allExprList) {
            String result = GenerateAllExpr(allExpr, preTab + "\t");
            if (result == null) return null;
            content.append(result);
        }
        content.append(preTab)
                .append("until not(")
                .append(valueStatResult)
                .append(")\n");

        return content.toString();
    }

    @NotNull
    private String GenerateWrapExpr(ALittleWrapExpr rootExpr, String preTab) throws Exception {
        StringBuilder content = new StringBuilder(preTab + "do\n");
        List<ALittleAllExpr> allExprList = rootExpr.getAllExprList();
        for (ALittleAllExpr allExpr : allExprList) {
            String result = GenerateAllExpr(allExpr, preTab + "\t");
            if (result == null) return null;
            content.append(result);
        }
        content.append(preTab + "end\n");

        return content.toString();
    }

    @NotNull
    private String GenerateReturnExpr(ALittleReturnExpr rootExpr, String preTab) throws Exception {
        if (rootExpr.getReturnYield() != null) {
            return preTab + "return ___coroutine.yield()\n";
        }

        List<ALittleValueStat> valueStatList = rootExpr.getValueStatList();
        List<String> contentList = new ArrayList<>();
        for (ALittleValueStat valueStat : valueStatList) {
            contentList.add(GenerateValueStat(valueStat));
        }
        String valueStatResult = "";
        if (!contentList.isEmpty())
            valueStatResult = " " + String.join(", ", contentList);

        return preTab + "return" + valueStatResult + "\n";
    }

    @NotNull
    private String GenerateFlowExpr(ALittleFlowExpr root, String preTab) throws Exception {
        String content = root.getText();
        if (content.startsWith("break"))
            return preTab + "break\n";

        throw new Exception("未知的操作语句:" + content);
    }

    @NotNull
    private String GenerateAllExpr(ALittleAllExpr root, String preTab) throws Exception {
        PsiElement[] childList = root.getChildren();

        List<String> exprList = new ArrayList<>();
        for (PsiElement child : childList) {
            if (child instanceof ALittleFlowExpr) {
                exprList.add(GenerateFlowExpr((ALittleFlowExpr)child, preTab));
            } else if (child instanceof ALittleReturnExpr) {
                exprList.add(GenerateReturnExpr((ALittleReturnExpr)child, preTab));
            } else if (child instanceof ALittleDoWhileExpr) {
                exprList.add(GenerateDoWhileExpr((ALittleDoWhileExpr)child, preTab));
            } else if (child instanceof ALittleWhileExpr) {
                exprList.add(GenerateWhileExpr((ALittleWhileExpr)child, preTab));
            } else if (child instanceof ALittleForExpr) {
                exprList.add(GenerateForExpr((ALittleForExpr)child, preTab));
            } else if (child instanceof ALittleIfExpr) {
                exprList.add(GenerateIfExpr((ALittleIfExpr)child, preTab));
            } else if (child instanceof ALittleOpAssignExpr) {
                exprList.add(GenerateOpAssignExpr((ALittleOpAssignExpr)child, preTab));
            } else if (child instanceof ALittleVarAssignExpr) {
                exprList.add(GenerateVarAssignExpr((ALittleVarAssignExpr)child, preTab, "local "));
            } else if (child instanceof ALittleOp1Expr) {
                exprList.add(GenerateOp1Expr((ALittleOp1Expr)child, preTab));
            } else if (child instanceof ALittleWrapExpr) {
                exprList.add(GenerateWrapExpr((ALittleWrapExpr)child, preTab));
            } else if (child instanceof ALittlePropertyValueExpr) {
                exprList.add(GeneratePropertyValueExpr((ALittlePropertyValueExpr)child, preTab));
            }
        }

        return String.join("\n", exprList);
    }

    @NotNull
    private String GenerateEnum(ALittleEnumDec root, String preTab) throws Exception {
        ALittleEnumNameDec nameDec = root.getEnumNameDec();
        if (nameDec == null) throw new Exception(root.getText() + "没有定义枚举名");

        StringBuilder content = new StringBuilder();
        content.append(preTab)
                .append(nameDec.getIdContent().getText())
                .append(" = {\n");

        int enumValue = -1;
        String enumString = "-1";

        List<ALittleEnumVarDec> varDecList = root.getEnumVarDecList();
        for (ALittleEnumVarDec varDec : varDecList) {
            if (varDec.getDigitContent() != null) {
                String value = varDec.getDigitContent().getText();
                if (!PsiHelper.isInt(value)) {
                    throw new Exception(varDec.getIdContent().getText() + "对应的枚举值必须是整数");
                }
                if (value.startsWith("0x"))
                    enumValue = Integer.parseInt(value.substring(2), 16);
                else
                    enumValue = Integer.parseInt(value);
                enumString = value;
            } else if (varDec.getStringContent() != null) {
                enumString = varDec.getStringContent().getText();
            } else {
                ++ enumValue;
                enumString = "" + enumValue;
            }
            
            content.append(preTab)
                    .append("\t")
                    .append(varDec.getIdContent().getText())
                    .append(" = ")
                    .append(enumString)
                    .append(",\n");
        }

        content.append(preTab).append("}\n\n");

        return content.toString();
    }

    @NotNull
    private String GenerateClass(ALittleClassDec root, String preTab) throws Exception {
        ALittleClassNameDec nameDec = root.getClassNameDec();
        if (nameDec == null) {
            throw new Exception("类没有定义类名");
        }

        //类声明//////////////////////////////////////////////////////////////////////////////////////////
        String className = nameDec.getIdContent().getText();
        StringBuilder content = new StringBuilder(preTab + className);

        String namespacePre = "ALittle.";
        if (mNamespaceName.equals("ALittle")) namespacePre = "";

        ALittleClassExtendsDec extendsDec = root.getClassExtendsDec();
        String extendsName = "";
        if (extendsDec != null) {
            if (extendsDec.getNamespaceNameDec() != null) {
                extendsName += extendsDec.getNamespaceNameDec().getText() + ".";
            }
            if (extendsDec.getClassNameDec() != null) {
                extendsName += extendsDec.getClassNameDec().getText();
            }
        }
        if (extendsName.equals("")) {
            extendsName = "nil";
        }

        content.append(" = ").append(namespacePre).append("Class(").append(extendsName).append(", \"").append(className).append("\")\n\n");

        //构建构造函数//////////////////////////////////////////////////////////////////////////////////////////
        String ctorParamList = "";
        List<ALittleClassCtorDec> ctorDecList = root.getClassCtorDecList();
        if (ctorDecList.size() > 1) {
            throw new Exception("class " + className + " 最多只能有一个构造函数");
        }
        if (!ctorDecList.isEmpty()) {
            ALittleClassCtorDec ctorDec = ctorDecList.get(0);
            List<String> paramNameList = new ArrayList<>();

            ALittleMethodParamDec paramDec = ctorDec.getMethodParamDec();
            if (paramDec != null) {
                List<ALittleMethodParamOneDec> paramOneDecList = paramDec.getMethodParamOneDecList();
                for (ALittleMethodParamOneDec paramOneDec : paramOneDecList) {
                    ALittleMethodParamNameDec paramNameDec = paramOneDec.getMethodParamNameDec();
                    if (paramNameDec == null) {
                        throw new Exception("class " + className + " 的构造函数没有参数名");
                    }
                    paramNameList.add(paramNameDec.getIdContent().getText());
                }
            }
            ctorParamList = String.join(", ", paramNameList);
            content.append(preTab)
                    .append("function ")
                    .append(className)
                    .append(":Ctor(").append(ctorParamList).append(")\n");

            mOpenRawSet = true;

            ALittleMethodBodyDec bodyDec = ctorDec.getMethodBodyDec();
            StringBuilder allExprContent = new StringBuilder();
            if (bodyDec != null) {
                List<ALittleAllExpr> allExprList = bodyDec.getAllExprList();
                for (ALittleAllExpr allExpr : allExprList) {
                    allExprContent.append(GenerateAllExpr(allExpr, preTab + "\t"));
                }
            }

            mOpenRawSet = false;

            content.append(allExprContent);
            content.append(preTab).append("end\n");

            content.append("\n");
        }
        //构建getter函数///////////////////////////////////////////////////////////////////////////////////////
        List<ALittleClassGetterDec> classGetterDecList = root.getClassGetterDecList();
        for (ALittleClassGetterDec classGetterDec : classGetterDecList) {
            ALittleMethodNameDec classMethodNameDec = classGetterDec.getMethodNameDec();
            if (classMethodNameDec == null) {
                throw new Exception("class " + className + " getter函数没有函数名");
            }
            content.append(preTab)
                    .append("function ")
                    .append(className)
                    .append(".__getter:")
                    .append(classMethodNameDec.getIdContent().getText())
                    .append("()\n");

            ALittleMethodBodyDec classMethodBodyDec = classGetterDec.getMethodBodyDec();
            if (classMethodBodyDec == null) {
                throw new Exception("class " + className + " getter函数没有函数体");
            }
            List<ALittleAllExpr> allExprList = classMethodBodyDec.getAllExprList();
            for (ALittleAllExpr allExpr : allExprList) {
                content.append(GenerateAllExpr(allExpr, preTab + "\t"));
            }
            content.append(preTab).append("end\n");

            content.append("\n");
        }
        //构建setter函数///////////////////////////////////////////////////////////////////////////////////////
        List<ALittleClassSetterDec> classSetterDecList = root.getClassSetterDecList();
        for (ALittleClassSetterDec classSetterDec : classSetterDecList) {
            ALittleMethodNameDec classMethodNameDec = classSetterDec.getMethodNameDec();
            if (classMethodNameDec == null) {
                throw new Exception("class " + className + " setter函数没有函数名");
            }
            ALittleMethodParamOneDec paramDec = classSetterDec.getMethodParamOneDec();
            if (paramDec == null) {
                throw new Exception("class " + className + " setter函数必须要有一个参数");
            }
            ALittleMethodParamNameDec paramNameDec = paramDec.getMethodParamNameDec();
            if (paramNameDec == null) {
                throw new Exception("class " + className + " 函数没有定义函数名");
            }
            content.append(preTab)
                    .append("function ")
                    .append(className)
                    .append(".__setter:")
                    .append(classMethodNameDec.getIdContent().getText())
                    .append("(")
                    .append(paramNameDec.getIdContent().getText())
                    .append(")\n");

            ALittleMethodBodyDec classMethodBodyDec = classSetterDec.getMethodBodyDec();
            if (classMethodBodyDec == null) {
                throw new Exception("class " + className + " setter函数没有函数体");
            }
            List<ALittleAllExpr> allExprList = classMethodBodyDec.getAllExprList();
            for (ALittleAllExpr allExpr : allExprList) {
                content.append(GenerateAllExpr(allExpr, preTab + "\t"));
            }
            content.append(preTab).append("end\n");

            content.append("\n");
        }
        //构建成员函数//////////////////////////////////////////////////////////////////////////////////////////
        List<ALittleClassMethodDec> classMethodDecList = root.getClassMethodDecList();
        for (ALittleClassMethodDec classMethodDec : classMethodDecList) {
            ALittleMethodNameDec classMethodNameDec = classMethodDec.getMethodNameDec();
            if (classMethodNameDec == null) {
                throw new Exception("class " + className + " 成员函数没有函数名");
            }

            List<String> paramNameList = new ArrayList<>();
            ALittleMethodParamDec paramDec = classMethodDec.getMethodParamDec();
            if (paramDec != null) {
                List<ALittleMethodParamOneDec> paramOneDecList = paramDec.getMethodParamOneDecList();
                for (ALittleMethodParamOneDec paramOneDec : paramOneDecList) {
                    ALittleMethodParamNameDec paramNameDec = paramOneDec.getMethodParamNameDec();
                    if (paramNameDec == null) {
                        throw new Exception("class " + className + " 成员函数没有参数名");
                    }
                    paramNameList.add(paramNameDec.getIdContent().getText());
                }
            }
            String methodParamList = String.join(", ", paramNameList);
            content.append(preTab)
                    .append("function ")
                    .append(className)
                    .append(":")
                    .append(classMethodNameDec.getText())
                    .append("(")
                    .append(methodParamList)
                    .append(")\n");

            ALittleMethodBodyDec classMethodBodyDec = classMethodDec.getMethodBodyDec();
            if (classMethodBodyDec == null) {
                throw new Exception("class " + className + " 成员函数没有函数体");
            }
            List<ALittleAllExpr> allExprList = classMethodBodyDec.getAllExprList();
            for (ALittleAllExpr allExpr : allExprList) {
                content.append(GenerateAllExpr(allExpr, preTab + "\t"));
            }
            content.append(preTab).append("end\n");

            if (classMethodDec.getCoModifier() != null && classMethodDec.getCoModifier().getText().equals("async")) {
                content.append(preTab)
                        .append(className).append(".").append(classMethodNameDec.getIdContent().getText())
                        .append(" = ").append(namespacePre).append("CoWrap(")
                        .append(className).append(".").append(classMethodNameDec.getIdContent().getText())
                        .append(")\n");
            }

            content.append("\n");
        }
        //构建静态函数//////////////////////////////////////////////////////////////////////////////////////////
        List<ALittleClassStaticDec> classStaticDecList = root.getClassStaticDecList();
        for (ALittleClassStaticDec classStaticDec : classStaticDecList) {
            ALittleMethodNameDec classMethodNameDec = classStaticDec.getMethodNameDec();
            if (classMethodNameDec == null) {
                throw new Exception("class " + className + " 静态函数没有函数名");
            }
            List<String> paramNameList = new ArrayList<>();
            ALittleMethodParamDec paramDec = classStaticDec.getMethodParamDec();
            if (paramDec != null) {
                List<ALittleMethodParamOneDec> paramOneDecList = paramDec.getMethodParamOneDecList();
                for (ALittleMethodParamOneDec paramOneDec : paramOneDecList) {
                    ALittleMethodParamNameDec paramNameDec = paramOneDec.getMethodParamNameDec();
                    if (paramNameDec == null) {
                        throw new Exception("class " + className + " 静态函数没有参数名");
                    }
                    paramNameList.add(paramNameDec.getIdContent().getText());
                }
            }

            String methodParamList = String.join(", ", paramNameList);
            content.append(preTab)
                    .append("function ")
                    .append(className)
                    .append(".")
                    .append(classMethodNameDec.getText())
                    .append("(")
                    .append(methodParamList)
                    .append(")\n");

            ALittleMethodBodyDec classMethodBodyDec = classStaticDec.getMethodBodyDec();
            if (classMethodBodyDec == null) {
                throw new Exception("class " + className + " 静态函数没有函数体");
            }
            List<ALittleAllExpr> allExprList = classMethodBodyDec.getAllExprList();
            for (ALittleAllExpr allExpr : allExprList) {
                content.append(GenerateAllExpr(allExpr, preTab + "\t"));
            }
            content.append(preTab).append("end\n");

            if (classStaticDec.getCoModifier() != null && classStaticDec.getCoModifier().getText().equals("async")) {
                content.append(preTab)
                        .append(className).append(".").append(classMethodNameDec.getIdContent().getText())
                        .append(" = ").append(namespacePre).append("CoWrap(")
                        .append(className).append(".").append(classMethodNameDec.getIdContent().getText())
                        .append(")\n");
            }
            content.append("\n");
        }
        ////////////////////////////////////////////////////////////////////////////////////////////

        return content.toString();
    }

    @NotNull
    private String GenerateInstance(ALittleInstanceDec root, String preTab) throws Exception {
        ALittleVarAssignExpr varAssignExpr = root.getVarAssignExpr();
        List<ALittleVarAssignDec> pairDecList = varAssignExpr.getVarAssignDecList();
        if (pairDecList.isEmpty()) {
            throw new Exception("局部变量没有变量名:" + root.getText());
        }

        List<String> nameList = new ArrayList<>();
        for (ALittleVarAssignDec pairDec : pairDecList) {
            nameList.add(pairDec.getVarAssignNameDec().getIdContent().getText());
        }

        String content = preTab;

        ALittleAccessModifier accessModifierDec = root.getAccessModifier();
        if (accessModifierDec == null || accessModifierDec.getText().equals("private")) {
            content += "local ";
            content += String.join(", ", nameList);
        } else if (accessModifierDec.getText().equals("protected")) {
            content += String.join(", ", nameList);
        } else if (accessModifierDec.getText().equals("public")) {
            content += "_G.";
            content += String.join(", _G.", nameList);
        }

        ALittleValueStat valueStat = varAssignExpr.getValueStat();
        if (valueStat == null) {
            return content + " = nil\n";
        }
        return content + " = " + GenerateValueStat(valueStat) + "\n";
    }

    @NotNull
    private String GenerateGlobalMethod(ALittleGlobalMethodDec root, String preTab) throws Exception {
        ALittleMethodNameDec globalMethodNameDec = root.getMethodNameDec();
        if (globalMethodNameDec == null) {
            throw new Exception("全局函数没有函数名");
        }

        String namespacePre = "ALittle.";
        if (mNamespaceName.equals("ALittle")) namespacePre = "";

        String methodName = globalMethodNameDec.getIdContent().getText();

        List<String> paramNameList = new ArrayList<>();
        ALittleMethodParamDec paramDec = root.getMethodParamDec();
        if (paramDec != null) {
            List<ALittleMethodParamOneDec> paramOneDecList = paramDec.getMethodParamOneDecList();
            for (ALittleMethodParamOneDec paramOneDec : paramOneDecList) {
                ALittleMethodParamNameDec paramNameDec = paramOneDec.getMethodParamNameDec();
                if (paramNameDec == null) {
                    throw new Exception("全局函数" + methodName + "没有参数名");
                }
                paramNameList.add(paramNameDec.getIdContent().getText());
            }
        }

        StringBuilder content = new StringBuilder();
        String methodParamList = String.join(", ", paramNameList);
        content.append(preTab)
                .append("function ")
                .append(methodName)
                .append("(")
                .append(methodParamList)
                .append(")\n");

        ALittleMethodBodyDec classMethodBodyDec = root.getMethodBodyDec();
        if (classMethodBodyDec == null) {
            throw new Exception("全局函数 " + methodName + " 没有函数体");
        }
        List<ALittleAllExpr> allExprList = classMethodBodyDec.getAllExprList();
        for (ALittleAllExpr allExpr : allExprList) {
            content.append(GenerateAllExpr(allExpr, preTab + "\t"));
        }
        content.append(preTab).append("end\n");

        // 协程判定
        if (root.getCoModifier() != null && root.getCoModifier().getText().equals("async")) {
            content.append(preTab).append(methodName)
                    .append(" = ").append(namespacePre).append("CoWrap(")
                    .append(methodName).append(")\n");
        }

        content.append("\n");

        return content.toString();
    }

    @NotNull
    private String GenerateNamespace(ALittleNamespaceDec root) throws Exception {
        ALittleNamespaceNameDec nameDec = root.getNamespaceNameDec();
        if (nameDec == null) {
            throw new Exception("命名域没有定义名字");
        }
        mNamespaceName = nameDec.getIdContent().getText();

        StringBuilder content;
        if (mNamespaceName.equals("lua"))
            content = new StringBuilder("\n");
        else
            content = new StringBuilder("\nmodule(\"" + mNamespaceName + "\", package.seeall)\n\n");

        StringBuilder otherContent = new StringBuilder();
        PsiElement[] childList = root.getChildren();
        for (PsiElement child : childList) {
            // 处理结构体
            if (child instanceof ALittleStructDec) {
            // 处理enum
            } else if (child instanceof ALittleEnumDec) {
                otherContent.append(GenerateEnum((ALittleEnumDec) child, ""));
            // 处理class
            } else if (child instanceof ALittleClassDec) {
                otherContent.append(GenerateClass((ALittleClassDec) child, ""));
            // 处理instance
            } else if (child instanceof ALittleInstanceDec) {
                otherContent.append(GenerateInstance((ALittleInstanceDec)child, ""));
            // 处理全局函数
            } else if (child instanceof ALittleGlobalMethodDec) {
                otherContent.append(GenerateGlobalMethod((ALittleGlobalMethodDec)child, ""));
            }
        }

        if (mRawsetUseCount > 0)
            content.append("local ___rawset = rawset\n");
        content.append("local ___pairs = pairs\n");
        content.append("local ___ipairs = ipairs\n");
        content.append("local ___coroutine = coroutine\n");
        content.append("\n");

        content.append(otherContent);

        return content.toString();
    }
}
