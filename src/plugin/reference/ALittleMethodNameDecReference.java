package plugin.reference;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import plugin.ALittleIcons;
import plugin.ALittleTreeChangeListener;
import plugin.ALittleUtil;
import plugin.psi.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ALittleMethodNameDecReference extends ALittleReference<ALittleMethodNameDec> {
    public ALittleMethodNameDecReference(@NotNull ALittleMethodNameDec element, TextRange textRange) {
        super(element, textRange);
    }

    public ALittleReferenceUtil.GuessTypeInfo guessTypeForGetter() throws ALittleReferenceUtil.ALittleReferenceException {
        ALittleClassGetterDec classGetterDec = (ALittleClassGetterDec) myElement.getParent();

        ALittleReferenceUtil.GuessTypeInfo info = new ALittleReferenceUtil.GuessTypeInfo();
        info.type = ALittleReferenceUtil.GuessType.GT_FUNCTOR;
        info.value = "Functor<(";
        info.element = classGetterDec;
        info.functorAwait = false;
        info.functorParamList = new ArrayList<>();
        info.functorParamNameList = new ArrayList<>();
        info.functorReturnList = new ArrayList<>();

        // 第一个参数是类
        ALittleReferenceUtil.GuessTypeInfo classGuessInfo = ((ALittleClassDec)classGetterDec.getParent()).guessType();
        info.functorParamList.add(classGuessInfo);
        info.functorParamNameList.add(classGuessInfo.value);
        info.value += classGuessInfo.value + ")";

        List<String> typeList = new ArrayList<>();
        // 添加返回值列表
        ALittleAllType allType = classGetterDec.getAllType();
        if (allType == null) throw new ALittleReferenceUtil.ALittleReferenceException(myElement, "指向的getter函数没有定义返回值");
        ALittleReferenceUtil.GuessTypeInfo GuessInfo = allType.guessType();
        typeList.add(GuessInfo.value);
        info.functorReturnList.add(GuessInfo);

        if (!typeList.isEmpty()) info.value += ":";
        info.value += String.join(",", typeList) + ">";
        return info;
    }

    public ALittleReferenceUtil.GuessTypeInfo guessTypeForSetter() throws ALittleReferenceUtil.ALittleReferenceException {
        ALittleClassSetterDec classSetterDec = (ALittleClassSetterDec) myElement.getParent();

        ALittleReferenceUtil.GuessTypeInfo info = new ALittleReferenceUtil.GuessTypeInfo();
        info.type = ALittleReferenceUtil.GuessType.GT_FUNCTOR;
        info.value = "Functor<(";
        info.element = classSetterDec;
        info.functorAwait = false;
        info.functorParamList = new ArrayList<>();
        info.functorParamNameList = new ArrayList<>();
        info.functorReturnList = new ArrayList<>();

        List<String> typeList = new ArrayList<>();
        // 第一个参数是类
        ALittleReferenceUtil.GuessTypeInfo classGuessInfo = ((ALittleClassDec)classSetterDec.getParent()).guessType();
        typeList.add(classGuessInfo.value);
        info.functorParamList.add(classGuessInfo);
        info.functorParamNameList.add(classGuessInfo.value);

        // 添加参数列表
        ALittleMethodParamOneDec oneDec = classSetterDec.getMethodParamOneDec();
        if (oneDec == null) throw new ALittleReferenceUtil.ALittleReferenceException(myElement, "指向的setter函数没有定义参数");

        ALittleReferenceUtil.GuessTypeInfo guessInfo = oneDec.getAllType().guessType();
        typeList.add(guessInfo.value);
        info.functorParamList.add(guessInfo);
        if (oneDec.getMethodParamNameDec() != null) {
            info.functorParamNameList.add(oneDec.getMethodParamNameDec().getIdContent().getText());
        } else {
            info.functorParamNameList.add("");
        }

        info.value += String.join(",", typeList) + ")";
        info.value += ">";
        return info;
    }

    @NotNull
    public List<ALittleReferenceUtil.GuessTypeInfo> guessTypes() throws ALittleReferenceUtil.ALittleReferenceException {
        List<ALittleReferenceUtil.GuessTypeInfo> guessList = new ArrayList<>();
        ResolveResult[] resultList = multiResolve(false);
        for (ResolveResult result : resultList) {
            PsiElement element = result.getElement();
            if (element instanceof ALittleMethodNameDec) {
                PsiElement parent = element.getParent();
                // 处理getter
                if (parent instanceof ALittleClassGetterDec) {
                    guessList.add(guessTypeForGetter());
                } else if (parent instanceof ALittleClassSetterDec) {
                    guessList.add(guessTypeForSetter());
                } else if (parent instanceof ALittleClassMethodDec) {
                    ALittleClassMethodDec classMethodDec = (ALittleClassMethodDec) parent;

                    ALittleReferenceUtil.GuessTypeInfo info = new ALittleReferenceUtil.GuessTypeInfo();
                    info.type = ALittleReferenceUtil.GuessType.GT_FUNCTOR;
                    info.value = "Functor<(";
                    info.element = classMethodDec;
                    info.functorAwait = classMethodDec.getCoModifier() != null && classMethodDec.getCoModifier().getText().equals("await");
                    if (info.functorAwait) {
                        info.value = "Functor<await(";
                    }
                    info.functorParamList = new ArrayList<>();
                    info.functorParamNameList = new ArrayList<>();
                    info.functorReturnList = new ArrayList<>();

                    List<String> typeList = new ArrayList<>();
                    // 第一个参数是类
                    ALittleReferenceUtil.GuessTypeInfo classGuessInfo = ((ALittleClassDec)classMethodDec.getParent()).guessType();
                    typeList.add(classGuessInfo.value);
                    info.functorParamList.add(classGuessInfo);
                    info.functorParamNameList.add(classGuessInfo.value);

                    // 添加参数列表
                    ALittleMethodParamDec paramDec = classMethodDec.getMethodParamDec();
                    if (paramDec != null) {
                        List<ALittleMethodParamOneDec> oneDecList = paramDec.getMethodParamOneDecList();
                        for (ALittleMethodParamOneDec oneDec : oneDecList) {
                            ALittleAllType allType = oneDec.getAllType();
                            ALittleReferenceUtil.GuessTypeInfo GuessInfo = allType.guessType();
                            typeList.add(GuessInfo.value);
                            info.functorParamList.add(GuessInfo);
                            if (oneDec.getMethodParamNameDec() != null) {
                                info.functorParamNameList.add(oneDec.getMethodParamNameDec().getIdContent().getText());
                            } else {
                                info.functorParamNameList.add("");
                            }
                        }
                    }
                    info.value += String.join(",", typeList) + ")";
                    typeList = new ArrayList<>();
                    // 添加返回值列表
                    ALittleMethodReturnDec returnDec = classMethodDec.getMethodReturnDec();
                    if (returnDec != null) {
                        List<ALittleAllType> allTypeList = returnDec.getAllTypeList();
                        for (ALittleAllType allType : allTypeList) {
                            ALittleReferenceUtil.GuessTypeInfo GuessInfo = allType.guessType();
                            typeList.add(GuessInfo.value);
                            info.functorReturnList.add(GuessInfo);
                        }
                    }
                    if (!typeList.isEmpty()) info.value += ":";
                    info.value += String.join(",", typeList) + ">";
                    guessList.add(info);
                } else if (parent instanceof ALittleClassStaticDec) {
                    ALittleClassStaticDec classStaticDec = (ALittleClassStaticDec) parent;

                    ALittleReferenceUtil.GuessTypeInfo info = new ALittleReferenceUtil.GuessTypeInfo();
                    info.type = ALittleReferenceUtil.GuessType.GT_FUNCTOR;
                    info.value = "Functor<(";
                    info.element = classStaticDec;
                    info.functorAwait = classStaticDec.getCoModifier() != null && classStaticDec.getCoModifier().getText().equals("await");
                    if (info.functorAwait) {
                        info.value = "Functor<await(";
                    }
                    info.functorParamList = new ArrayList<>();
                    info.functorParamNameList = new ArrayList<>();
                    info.functorReturnList = new ArrayList<>();

                    List<String> typeList = new ArrayList<>();
                    ALittleMethodParamDec paramDec = classStaticDec.getMethodParamDec();
                    if (paramDec != null) {
                        List<ALittleMethodParamOneDec> oneDecList = paramDec.getMethodParamOneDecList();
                        for (ALittleMethodParamOneDec oneDec : oneDecList) {
                            ALittleAllType allType = oneDec.getAllType();
                            ALittleReferenceUtil.GuessTypeInfo GuessInfo = allType.guessType();
                            typeList.add(GuessInfo.value);
                            info.functorParamList.add(GuessInfo);
                            if (oneDec.getMethodParamNameDec() != null) {
                                info.functorParamNameList.add(oneDec.getMethodParamNameDec().getIdContent().getText());
                            } else {
                                info.functorParamNameList.add("");
                            }
                        }
                    }
                    info.value += String.join(",", typeList) + ")";
                    typeList = new ArrayList<>();
                    ALittleMethodReturnDec returnDec = classStaticDec.getMethodReturnDec();
                    if (returnDec != null) {
                        List<ALittleAllType> allTypeList = returnDec.getAllTypeList();
                        for (ALittleAllType allType : allTypeList) {
                            ALittleReferenceUtil.GuessTypeInfo GuessInfo = allType.guessType();
                            typeList.add(GuessInfo.value);
                            info.functorReturnList.add(GuessInfo);
                        }
                    }
                    if (!typeList.isEmpty()) info.value += ":";
                    info.value += String.join(",", typeList) + ">";
                    guessList.add(info);
                } else if (parent instanceof ALittleGlobalMethodDec) {
                    ALittleGlobalMethodDec globalMethodDec = (ALittleGlobalMethodDec) parent;

                    ALittleReferenceUtil.GuessTypeInfo info = new ALittleReferenceUtil.GuessTypeInfo();
                    info.type = ALittleReferenceUtil.GuessType.GT_FUNCTOR;
                    info.value = "Functor<(";
                    info.element = globalMethodDec;
                    info.functorAwait = globalMethodDec.getCoModifier() != null && globalMethodDec.getCoModifier().getText().equals("await");
                    if (info.functorAwait) {
                        info.value = "Functor<await(";
                    }
                    info.functorParamList = new ArrayList<>();
                    info.functorParamNameList = new ArrayList<>();
                    info.functorReturnList = new ArrayList<>();

                    List<String> typeList = new ArrayList<>();
                    ALittleMethodParamDec paramDec = globalMethodDec.getMethodParamDec();
                    if (paramDec != null) {
                        List<ALittleMethodParamOneDec> oneDecList = paramDec.getMethodParamOneDecList();
                        for (ALittleMethodParamOneDec oneDec : oneDecList) {
                            ALittleAllType allType = oneDec.getAllType();
                            ALittleReferenceUtil.GuessTypeInfo GuessInfo = allType.guessType();
                            typeList.add(GuessInfo.value);
                            info.functorParamList.add(GuessInfo);
                            if (oneDec.getMethodParamNameDec() != null) {
                                info.functorParamNameList.add(oneDec.getMethodParamNameDec().getIdContent().getText());
                            } else {
                                info.functorParamNameList.add("");
                            }
                        }
                    }
                    info.value += String.join(",", typeList) + ")";
                    typeList = new ArrayList<>();
                    ALittleMethodReturnDec returnDec = globalMethodDec.getMethodReturnDec();
                    if (returnDec != null) {
                        List<ALittleAllType> allTypeList = returnDec.getAllTypeList();
                        for (ALittleAllType allType : allTypeList) {
                            ALittleReferenceUtil.GuessTypeInfo GuessInfo = allType.guessType();
                            typeList.add(GuessInfo.value);
                            info.functorReturnList.add(GuessInfo);
                        }
                    }
                    if (!typeList.isEmpty()) info.value += ":";
                    info.value += String.join(",", typeList) + ">";
                    guessList.add(info);
                }
            }
        }

        return guessList;
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        List<ALittleMethodNameDec> decList = new ArrayList<>();

        PsiElement method_dec = myElement.getParent();
        if (method_dec.getParent() instanceof ALittleClassDec) {
            ALittleClassDec classDec = (ALittleClassDec) method_dec.getParent();
            ALittleUtil.findMethodNameDecList(project, mNamespace, classDec, mKey, decList, null, 100);
        } else if (method_dec.getParent() instanceof ALittleNamespaceDec) {
            decList = ALittleTreeChangeListener.findGlobalMethodNameDecList(project, mNamespace, mKey);
        }
        List<ResolveResult> results = new ArrayList<>();
        for (ALittleMethodNameDec dec : decList) {
            results.add(new PsiElementResolveResult(dec));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        PsiElement method_dec = myElement.getParent();
        List<LookupElement> variants = new ArrayList<>();
        // 类内部的函数
        if (method_dec.getParent() instanceof ALittleClassDec) {
            ALittleClassDec classDec = (ALittleClassDec) method_dec.getParent();

            List<ALittleMethodNameDec> decList = new ArrayList<>();
            List<Icon> iconList = new ArrayList<>();
            ALittleUtil.findMethodNameDecList(project, mNamespace, classDec, "", decList, iconList, 100);
            for (int i = 0; i < decList.size(); ++i) {
                ALittleMethodNameDec dec = decList.get(i);
                Icon icon = null;
                if (i < iconList.size()) icon = iconList.get(i);
                variants.add(LookupElementBuilder.create(dec.getText()).
                        withIcon(icon).
                        withTypeText(dec.getContainingFile().getName())
                );
            }
        // 全局函数
        } else if (method_dec.getParent() instanceof ALittleNamespaceDec) {
            List<ALittleMethodNameDec> decList = ALittleTreeChangeListener.findGlobalMethodNameDecList(project, mNamespace, "");
            for (ALittleMethodNameDec dec : decList) {
                variants.add(LookupElementBuilder.create(dec.getText()).
                        withIcon(ALittleIcons.GLOBAL_METHOD).
                        withTypeText(dec.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }
}
