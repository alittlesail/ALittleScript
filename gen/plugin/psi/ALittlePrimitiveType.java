// This is a generated file. Not intended for manual editing.
package plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface ALittlePrimitiveType extends PsiElement {

  PsiElement guessType();

  @NotNull
  List<PsiElement> guessTypes();

  PsiReference getReference();

  PsiElement getNameIdentifier();

  PsiElement setName(String new_name);

}
