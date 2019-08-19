// This is a generated file. Not intended for manual editing.
package plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import plugin.reference.ALittleReferenceUtil.GuessTypeInfo;
import plugin.reference.ALittleReferenceUtil.ALittleReferenceException;

public interface ALittleOp7Suffix extends PsiElement {

  @Nullable
  ALittleOp2Value getOp2Value();

  @NotNull
  ALittleOp7 getOp7();

  @NotNull
  List<ALittleOp7SuffixEe> getOp7SuffixEeList();

  @Nullable
  ALittleValueFactorStat getValueFactorStat();

  @NotNull
  GuessTypeInfo guessType() throws ALittleReferenceException;

  @NotNull
  List<GuessTypeInfo> guessTypes() throws ALittleReferenceException;

  PsiReference getReference();

  void checkError() throws ALittleReferenceException;

}
