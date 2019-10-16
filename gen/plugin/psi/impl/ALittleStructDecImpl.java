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

public class ALittleStructDecImpl extends ASTWrapperPsiElement implements ALittleStructDec {

  public ALittleStructDecImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ALittleVisitor visitor) {
    visitor.visitStructDec(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ALittleVisitor) accept((ALittleVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ALittleAccessModifier getAccessModifier() {
    return findChildByClass(ALittleAccessModifier.class);
  }

  @Override
  @Nullable
  public ALittleCsvModifier getCsvModifier() {
    return findChildByClass(ALittleCsvModifier.class);
  }

  @Override
  @Nullable
  public ALittleMysqlModifier getMysqlModifier() {
    return findChildByClass(ALittleMysqlModifier.class);
  }

  @Override
  @Nullable
  public ALittleStructExtendsDec getStructExtendsDec() {
    return findChildByClass(ALittleStructExtendsDec.class);
  }

  @Override
  @Nullable
  public ALittleStructNameDec getStructNameDec() {
    return findChildByClass(ALittleStructNameDec.class);
  }

  @Override
  @NotNull
  public List<ALittleStructVarDec> getStructVarDecList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ALittleStructVarDec.class);
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
