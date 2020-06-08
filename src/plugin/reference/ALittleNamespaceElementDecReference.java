package plugin.reference;

import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import plugin.alittle.PsiHelper;
import plugin.guess.ALittleGuess;
import plugin.guess.ALittleGuessException;
import plugin.guess.ALittleGuessNamespace;
import plugin.psi.ALittleNamespaceDec;
import plugin.psi.ALittleNamespaceElementDec;
import plugin.psi.ALittleNamespaceNameDec;

import java.util.ArrayList;
import java.util.List;

public class ALittleNamespaceElementDecReference extends ALittleReference<ALittleNamespaceElementDec> {
    public ALittleNamespaceElementDecReference(@NotNull ALittleNamespaceElementDec element, TextRange textRange) {
        super(element, textRange);
    }

    @Override
    public void checkError() throws ALittleGuessException {
        PsiHelper.checkError(myElement, myElement.getModifierList());
    }
}
