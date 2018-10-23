package org.pitest.mutationtest.engine.gregor.mutators.AOD;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;


public class AODMutator1 implements MethodMutatorFactory {
    
    @Override
    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo, final MethodVisitor methodVisitor)  {
        return new AODVisitor1(this, context, methodInfo, methodVisitor);
    }
    @Override
    public String getGloballyUniqueId()  {
        return this.getClass().getName();
    }
    @Override
    public String toString() {
        return "AOD1";
    }
    
    @Override
    public String getName() {
        return toString();
    }
}

class AODVisitor1 extends MethodVisitor  {
    
    private final MethodMutatorFactory factory;
    private final MutationContext      context;
    private final MethodInfo      info;
    
    AODVisitor1(final MethodMutatorFactory factory,
                        final MutationContext context, final MethodInfo info, final MethodVisitor delegateMethodVisitor)  {
        super(Opcodes.ASM5, delegateMethodVisitor);
        this.factory = factory;
        this.context = context;
        this.info = info;
    }
    
    private boolean shouldMutate(int opcode) {
        if (info.isGeneratedEnumMethod()) {
            return false;
        } else {
            final MutationIdentifier newId = this.context.registerMutation(this.factory, "AOD1:" + opcode + " Replaced with first operand");
            return this.context.shouldMutate(newId);
        }
        
    }
    
    @Override
    public void visitInsn(int opcode) {
        switch (opcode) {
            case Opcodes.IADD:
            case Opcodes.ISUB:
            case Opcodes.IMUL:
            case Opcodes.IDIV:
            case Opcodes.IREM:
                
            case Opcodes.FADD:
            case Opcodes.FSUB:
            case Opcodes.FMUL:
            case Opcodes.FDIV:
            case Opcodes.FREM:
                if (this.shouldMutate(opcode))  {
                    mv.visitInsn(Opcodes.POP);
                } else  {
                    mv.visitInsn(opcode);
                }
                break;
            // seperate int/float from long/double because they both require different stack operations
            case Opcodes.LADD:
            case Opcodes.LSUB:
            case Opcodes.LMUL:
            case Opcodes.LDIV:
            case Opcodes.LREM:
                
            case Opcodes.DADD:
            case Opcodes.DSUB:
            case Opcodes.DMUL:
            case Opcodes.DDIV:
            case Opcodes.DREM:
                if (this.shouldMutate(opcode))  {
                    mv.visitInsn(Opcodes.POP2);
                } else  {
                    mv.visitInsn(opcode);
                }
                break;
            default:
                mv.visitInsn(opcode);
                break;
        }
    }
}
