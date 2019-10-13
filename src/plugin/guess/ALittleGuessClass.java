package plugin.guess;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import plugin.index.ALittleTreeChangeListener;
import plugin.psi.ALittleClassDec;
import plugin.reference.ALittleReferenceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ALittleGuessClass extends ALittleGuess {
    private @NotNull String mNamespaceName;
    private @NotNull String mClassName;

    public @NotNull List<ALittleGuess> templateList = new ArrayList<>();            // 类本身定义的模板列表
    public @NotNull Map<String, ALittleGuess> templateMap = new HashMap<>();        // 填充后的模板实例

    public String usingName;        // 如果是using定义出来的，那么就有这个值
    public @NotNull ALittleClassDec element;
    public ALittleGuessClass(@NotNull String namespaceName, @NotNull String className,
                             @NotNull ALittleClassDec e, String un) {
        mNamespaceName = namespaceName;
        mClassName = className;
        element = e;
        usingName = un;
    }

    @NotNull
    public ALittleGuessClass Clone() {
        ALittleGuessClass guess = new ALittleGuessClass(mNamespaceName, mClassName, element, usingName);
        guess.UpdateValue();
        return guess;
    }

    @NotNull
    public String GetNamespaceName() {
        return mNamespaceName;
    }

    @NotNull
    public String GetClassName() {
        return mClassName;
    }

    @Override
    public void UpdateValue() {
        value = mNamespaceName + "." + mClassName;
        List<String> nameList = new ArrayList<>();
        for (ALittleGuess tem : templateList) {
            ALittleGuess impl = templateMap.get(tem.value);
            if (impl == null) {
                nameList.add(tem.value);
            } else {
                nameList.add(impl.value);
            }
        }
        if (!nameList.isEmpty()) {
            value += "<" + String.join(",", nameList) + ">";
        }
    }

    @Override
    public boolean isChanged() {
        for (ALittleGuess guess : templateList) {
            if (guess.isChanged()) return true;
        }
        for (ALittleGuess guess : templateMap.values()) {
            if (guess.isChanged()) return true;
        }
        return ALittleTreeChangeListener.getGuessTypeList(element) == null;
    }
}