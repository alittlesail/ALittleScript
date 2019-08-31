package plugin.reference;

import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import plugin.psi.ALittleAllType;
import plugin.psi.ALittleTemplatePairDec;

import java.util.ArrayList;
import java.util.List;

public class ALittleTemplatePairDecReference extends ALittleReference<ALittleTemplatePairDec> {
    public ALittleTemplatePairDecReference(@NotNull ALittleTemplatePairDec element, TextRange textRange) {
        super(element, textRange);
    }

    @NotNull
    public List<ALittleReferenceUtil.GuessTypeInfo> guessTypes() throws ALittleReferenceUtil.ALittleReferenceException {
        List<ALittleReferenceUtil.GuessTypeInfo> guessList = new ArrayList<>();
        ALittleReferenceUtil.GuessTypeInfo info = new ALittleReferenceUtil.GuessTypeInfo();
        info.type = ALittleReferenceUtil.GuessType.GT_CLASS_TEMPLATE;
        info.value = myElement.getIdContent().getText();
        info.element = myElement;
        ALittleAllType allType = myElement.getAllType();
        if (allType != null) info.classTemplateExtends = allType.guessType();
        guessList.add(info);
        return guessList;
    }
}