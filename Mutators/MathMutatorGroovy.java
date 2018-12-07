/*
 * Copyright 2010 Henry Coles
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.pitest.mutationtest.engine.gregor.mutators;

import org.objectweb.asm.MethodVisitor;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.objectweb.asm.Opcodes;


public class MathMutatorGroovy implements MethodMutatorFactory {
    
    
    @Override
    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo, final MethodVisitor methodVisitor)  {
        return new MathVisitor(this, context, methodInfo, methodVisitor);
    }
    @Override
    public String toString() {
        return "MathMutator";
    }
    
    @Override
    public String getGloballyUniqueId() {
        return this.getClass().getName();
    }
    
    @Override
    public String getName() {
        return toString();
    }
    
}

class MathVisitor extends MethodVisitor {
    
    MathVisitor(final MethodMutatorFactory factory,
                final MutationContext context, final MethodInfo info, final MethodVisitor delegateMethodVisitor)  {
        super(Opcodes.ASM5, delegateMethodVisitor);
    }
    
    @Override
    public void visitLdcInsn(Object cst) {
        if (cst.equals("plus")) {
            super.visitLdcInsn("minus");
        } else if (cst.equals("minus")) {
            super.visitLdcInsn("plus");
        } else if (cst.equals("div")) {
            super.visitLdcInsn("multiply");
        } else if (cst.equals("multiply")) {
            super.visitLdcInsn("div");
        }
    }
    
    
}
