// This is a generated file. Not intended for manual editing.
package plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import plugin.guess.ALittleGuess;
import plugin.guess.ALittleGuessException;

public interface ALittleTemplateExtendsDec extends PsiElement {

  @Nullable
  ALittleAllType getAllType();

  @Nullable
  ALittleTemplateExtendsClassDec getTemplateExtendsClassDec();

  @Nullable
  ALittleTemplateExtendsStructDec getTemplateExtendsStructDec();

  @NotNull
  ALittleGuess guessType() throws ALittleGuessException;

  @NotNull
  List<ALittleGuess> guessTypes() throws ALittleGuessException;

  PsiReference getReference();

}