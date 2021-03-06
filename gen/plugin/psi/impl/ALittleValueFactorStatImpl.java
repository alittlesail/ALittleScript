// This is a generated file. Not intended for manual editing.
package plugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static plugin.psi.ALittleTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import plugin.psi.*;
import com.intellij.psi.PsiReference;
import plugin.guess.ALittleGuess;
import plugin.guess.ALittleGuessException;

public class ALittleValueFactorStatImpl extends ASTWrapperPsiElement implements ALittleValueFactorStat {

  public ALittleValueFactorStatImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ALittleVisitor visitor) {
    visitor.visitValueFactorStat(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ALittleVisitor) accept((ALittleVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ALittleConstValue getConstValue() {
    return findChildByClass(ALittleConstValue.class);
  }

  @Override
  @Nullable
  public ALittleCoroutineStat getCoroutineStat() {
    return findChildByClass(ALittleCoroutineStat.class);
  }

  @Override
  @Nullable
  public ALittleMethodParamTailDec getMethodParamTailDec() {
    return findChildByClass(ALittleMethodParamTailDec.class);
  }

  @Override
  @Nullable
  public ALittlePathsValue getPathsValue() {
    return findChildByClass(ALittlePathsValue.class);
  }

  @Override
  @Nullable
  public ALittlePropertyValue getPropertyValue() {
    return findChildByClass(ALittlePropertyValue.class);
  }

  @Override
  @Nullable
  public ALittleReflectValue getReflectValue() {
    return findChildByClass(ALittleReflectValue.class);
  }

  @Override
  @Nullable
  public ALittleWrapValueStat getWrapValueStat() {
    return findChildByClass(ALittleWrapValueStat.class);
  }

  @Override
  @NotNull
  public ALittleGuess guessType() throws ALittleGuessException {
    return ALittlePsiImplUtil.guessType(this);
  }

  @Override
  @NotNull
  public List<ALittleGuess> guessTypes() throws ALittleGuessException {
    return ALittlePsiImplUtil.guessTypes(this);
  }

  @Override
  public PsiReference getReference() {
    return ALittlePsiImplUtil.getReference(this);
  }

}
