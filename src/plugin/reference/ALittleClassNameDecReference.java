package plugin.reference;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import plugin.alittle.PsiHelper;
import plugin.component.ALittleIcons;
import plugin.index.ALittleIndex;
import plugin.index.ALittleTreeChangeListener;
import plugin.psi.ALittleClassDec;
import plugin.psi.ALittleClassExtendsDec;
import plugin.psi.ALittleClassNameDec;
import plugin.psi.ALittleNamespaceNameDec;

import java.util.ArrayList;
import java.util.List;

public class ALittleClassNameDecReference extends ALittleReference<ALittleClassNameDec> {
    public ALittleClassNameDecReference(@NotNull ALittleClassNameDec element, TextRange textRange) {
        super(element, textRange);

        // 如果父节点是extends，那么就获取指定的命名域
        PsiElement parent = element.getParent();
        if (parent instanceof ALittleClassExtendsDec) {
            ALittleNamespaceNameDec namespaceNameDec = ((ALittleClassExtendsDec)parent).getNamespaceNameDec();
            if (namespaceNameDec != null) {
                mNamespace = namespaceNameDec.getText();
            }
        }
    }

    @NotNull
    public List<ALittleReferenceUtil.GuessTypeInfo> guessTypes() throws ALittleReferenceUtil.ALittleReferenceException {
        List<ALittleReferenceUtil.GuessTypeInfo> guessList = new ArrayList<>();
        PsiElement parent = myElement.getParent();

        // 如果直接就是定义，那么直接获取
        if (parent instanceof ALittleClassDec) {
            guessList.add(((ALittleClassDec)parent).guessType());
        // 如果是继承那么就从继承那边获取
        } else if (parent instanceof ALittleClassExtendsDec) {
            List<PsiElement> classNameDecList = ALittleTreeChangeListener.findALittleNameDecList(myElement.getProject(),
                    PsiHelper.PsiElementType.CLASS_NAME, myElement.getContainingFile(), mNamespace, mKey, true);
            if (classNameDecList.isEmpty()) throw new ALittleReferenceUtil.ALittleReferenceException(myElement, "找不到类, namespace:" + mNamespace + ", key:" + mKey);
            for (PsiElement classNameDec : classNameDecList) {
                guessList.add(((ALittleClassNameDec)classNameDec).guessType());
            }
        } else {
            throw new ALittleReferenceUtil.ALittleReferenceException(myElement, "ALittleClassNameDec出现未知的父节点");
        }

        return guessList;
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        List<PsiElement> decList = ALittleTreeChangeListener.findALittleNameDecList(myElement.getProject(),
                PsiHelper.PsiElementType.CLASS_NAME, myElement.getContainingFile(), mNamespace, mKey, true);
        List<ResolveResult> results = new ArrayList<>();
        for (PsiElement dec : decList) {
            results.add(new PsiElementResolveResult(dec));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<PsiElement> decList = ALittleTreeChangeListener.findALittleNameDecList(myElement.getProject(),
                PsiHelper.PsiElementType.CLASS_NAME, myElement.getContainingFile(), mNamespace, "", true);
        List<LookupElement> variants = new ArrayList<>();
        for (PsiElement dec : decList) {
            variants.add(LookupElementBuilder.create(dec.getText()).
                    withIcon(ALittleIcons.CLASS).
                    withTypeText(dec.getContainingFile().getName())
            );
        }
        return variants.toArray();
    }

    public void checkError() throws ALittleReferenceUtil.ALittleReferenceException {
        if (myElement.getText().startsWith("___")) {
            throw new ALittleReferenceUtil.ALittleReferenceException(myElement, "类名不能以3个下划线开头");
        }

        List<ALittleReferenceUtil.GuessTypeInfo> guessList = guessTypes();
        if (guessList.isEmpty()) {
            throw new ALittleReferenceUtil.ALittleReferenceException(myElement, "未知类型");
        } else if (guessList.size() != 1) {
            throw new ALittleReferenceUtil.ALittleReferenceException(myElement, "重复定义");
        }
    }

    public void colorAnnotator(@NotNull AnnotationHolder holder) {
        Annotation anno = holder.createInfoAnnotation(myElement, null);
        anno.setTextAttributes(DefaultLanguageHighlighterColors.CLASS_NAME);
    }
}
