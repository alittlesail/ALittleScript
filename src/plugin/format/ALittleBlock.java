package plugin.format;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import plugin.psi.*;

import java.util.ArrayList;
import java.util.List;

public class ALittleBlock extends AbstractBlock {
    private SpacingBuilder m_spacingBuilder;

    protected ALittleBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment,
                           SpacingBuilder spacingBuilder) {
        super(node, wrap, alignment);
        m_spacingBuilder = spacingBuilder;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                Block block = new ALittleBlock(child,
                        Wrap.createWrap(WrapType.NONE, false),
                        Alignment.createAlignment(),
                        m_spacingBuilder);
                blocks.add(block);
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Override
    public Indent getIndent() {
        PsiElement element = myNode.getPsi();
        PsiElement parent = element.getParent();
        IElementType type = myNode.getElementType();

        if (element instanceof ALittleClassCtorDec
            || element instanceof ALittleClassMethodDec
            || element instanceof ALittleClassStaticDec
            || element instanceof ALittleClassSetterDec
            || element instanceof ALittleClassGetterDec
            || element instanceof ALittleClassVarDec)
            return Indent.getNormalIndent();

        if (element instanceof ALittleStructVarDec
            || element instanceof ALittleEnumVarDec)
            return Indent.getNormalIndent();

        if (element instanceof ALittleAllExpr)
            return Indent.getNormalIndent();

        if (type == ALittleTypes.LINE_COMMENT || type == ALittleTypes.BLOCK_COMMENT) {
            if (parent instanceof ALittleFile || parent instanceof ALittleNamespaceDec)
                return Indent.getNoneIndent();
            return Indent.getNormalIndent();
        }

        return Indent.getNoneIndent();
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int newChildIndex) {
        PsiElement element = myNode.getPsi();

        if (element instanceof ALittleStructDec
            || element instanceof ALittleEnumDec
            || element instanceof ALittleClassDec
            || element instanceof ALittleMethodBodyDec
            || element instanceof ALittleIfExpr
            || element instanceof ALittleElseIfExpr
            || element instanceof ALittleElseExpr
            || element instanceof ALittleForExpr
            || element instanceof ALittleWhileExpr
            || element instanceof ALittleWrapExpr)
            return new ChildAttributes(Indent.getNormalIndent(), null);

        return new ChildAttributes(Indent.getNoneIndent(), null);
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return m_spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}
