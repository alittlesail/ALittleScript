package plugin.reference;

import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import plugin.psi.*;

import java.util.ArrayList;
import java.util.List;

public class ALittlePropertyValueSuffixReference extends ALittleReference<ALittlePropertyValueSuffix> {
    public ALittlePropertyValueSuffixReference(@NotNull ALittlePropertyValueSuffix element, TextRange textRange) {
        super(element, textRange);
    }

    // 获取返回值类型
    @NotNull
    public List<ALittleReferenceUtil.GuessTypeInfo> guessTypes() throws ALittleReferenceUtil.ALittleReferenceException {
        if (myElement.getPropertyValueMethodCall() != null) {
            return myElement.getPropertyValueMethodCall().guessTypes();
        } else if (myElement.getPropertyValueDotId() != null) {
            return myElement.getPropertyValueDotId().guessTypes();
        } else if (myElement.getPropertyValueBracketValue() != null) {
            return myElement.getPropertyValueBracketValue().guessTypes();
        }
        return new ArrayList<>();
    }
}
