package org.pitest.mutationtest.engine.gregor.mutators.AOD;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public class AODMutator2 implements MethodMutatorFactory {
    
    private final class AODVisitor2 extends MethodVisitor {
        private final MutationContext context;
        
        AODVisitor2(final MutationContext context,
                              final MethodVisitor delegateVisitor) {
            super(Opcodes.ASM6, delegateVisitor);
            this.context = context;
        }
        
        @Override
        public void visitInsn(final int opcode) {
            if ((Opcodes.LADD == opcode) || (Opcodes.LSUB == opcode) || (Opcodes.LMUL == opcode) || (Opcodes.LDIV == opcode) || (Opcodes.LREM == opcode) || (Opcodes.DADD == opcode) || (Opcodes.DSUB == opcode) || (Opcodes.DMUL == opcode) || (Opcodes.DDIV == opcode) || (Opcodes.DREM == opcode)) {
                final MutationIdentifier mutationId = this.context.registerMutation(new AODMutator2(),
                                                                                    "AOD2:" + opcode + "Replaced with second operand");
                mv.visitInsn(Opcodes.DUP2_X2); // Duplicates the top two-word item on the stack and inserts the duplicate before the previous (two-word) item on the stack.
                //uses POP2 because long and doubles take up two words on the stack
                super.mv.visitInsn(Opcodes.POP2);
                super.mv.visitInsn(Opcodes.POP2);
            } else if ((Opcodes.IADD == opcode) || (Opcodes.ISUB == opcode) || (Opcodes.IDIV == opcode) || (Opcodes.IMUL == opcode)
                       || (Opcodes.IREM == opcode) || (Opcodes.FADD == opcode) || (Opcodes.FSUB == opcode) || (Opcodes.FMUL == opcode)
                       || (Opcodes.FDIV == opcode) || (Opcodes.FREM == opcode)) {
                final MutationIdentifier mutationId = this.context.registerMutation(new AODMutator2(),
                                                                                    "AOD2:" + opcode  + " Replaced with second operand");
                super.mv.visitInsn(Opcodes.SWAP); //Swaps the top two single-word items on the stack. word1/word2 cannot belong to a long or double. (can use for int and float)
                super.mv.visitInsn(Opcodes.POP);
            } else {
                super.visitInsn(opcode);
            }
        }
        
    }
    
    @Override
    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new AODVisitor2(context, methodVisitor);
    }
    
    @Override
    public String getGloballyUniqueId() {
        return this.getClass().getName();
    }
    
    @Override
    public String toString() {
        return "AOD2";
    }
    
    @Override
    public String getName() {
        return toString();
    }
    
}
