// This is a generated file. Not intended for manual editing.
package plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import plugin.guess.ALittleGuess;
import plugin.guess.ALittleGuessException;

public interface ALittleOp4Suffix extends PsiElement {

  @Nullable
  ALittleOp2Value getOp2Value();

  @NotNull
  ALittleOp4 getOp4();

  @NotNull
  List<ALittleOp4SuffixEe> getOp4SuffixEeList();

  @Nullable
  ALittleValueFactorStat getValueFactorStat();

  @NotNull
  ALittleGuess guessType() throws ALittleGuessException;

  @NotNull
  List<ALittleGuess> guessTypes() throws ALittleGuessException;

  PsiReference getReference();

}
