// This is a generated file. Not intended for manual editing.
package plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import plugin.reference.ALittleReferenceUtil.GuessTypeInfo;
import plugin.reference.ALittleReferenceUtil.ALittleReferenceException;

public interface ALittleOp7Stat extends PsiElement {

  @NotNull
  ALittleOp7Suffix getOp7Suffix();

  @NotNull
  List<ALittleOp7SuffixEx> getOp7SuffixExList();

  @NotNull
  ALittleValueFactorStat getValueFactorStat();

  @NotNull
  GuessTypeInfo guessType() throws ALittleReferenceException;

  @NotNull
  List<GuessTypeInfo> guessTypes() throws ALittleReferenceException;

  PsiReference getReference();

  void checkError() throws ALittleReferenceException;

}
