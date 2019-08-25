package plugin.reference;

import com.intellij.codeInsight.hints.InlayInfo;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import plugin.alittle.PsiHelper;
import plugin.psi.*;

import java.util.ArrayList;
import java.util.List;

public abstract class ALittleReference<T extends PsiElement> extends PsiReferenceBase<T> implements PsiPolyVariantReference, ALittleReferenceInterface {
    protected String mKey;
    protected String mNamespace;

    public ALittleReference(@NotNull T element, TextRange textRange) {
        super(element, textRange);
        mKey = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
        mNamespace = PsiHelper.getNamespaceName(element.getContainingFile());
    }

    public void checkError() throws ALittleReferenceUtil.ALittleReferenceException {
    }

    public void colorAnnotator(@NotNull AnnotationHolder holder) {
    }

    @NotNull
    public List<InlayInfo> getParameterHints() throws ALittleReferenceUtil.ALittleReferenceException {
        return new ArrayList<>();
    }

    @NotNull
    public ALittleReferenceUtil.GuessTypeInfo guessType() throws ALittleReferenceUtil.ALittleReferenceException {
        List<ALittleReferenceUtil.GuessTypeInfo> guessList = guessTypes();
        if (guessList.isEmpty()) {
            throw new ALittleReferenceUtil.ALittleReferenceException(myElement, "无法计算出该元素属于什么类型");
        }
        return guessList.get(0);
    }

    @NotNull
    public List<ALittleReferenceUtil.GuessTypeInfo> guessTypes() throws ALittleReferenceUtil.ALittleReferenceException {
        return new ArrayList<>();
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        return new ResolveResult[0];
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new LookupElement[0];
    }
}
