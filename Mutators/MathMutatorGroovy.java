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
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.objectweb.asm.Opcodes;


public class MathMutatorGroovy implements MethodMutatorFactory {
    
    
    @Override
    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo, final MethodVisitor methodVisitor)  {
        return new MathVisitorGroovy(this, context, methodInfo, methodVisitor);
    }
    @Override
    public String toString() {
        return "MathMutatorGroovy";
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

class MathVisitorGroovy extends MethodVisitor {
    
    private final MethodMutatorFactory factory;
    private final MutationContext      context;
    private final MethodInfo      info;
    
    MathVisitorGroovy(final MethodMutatorFactory factory, final MutationContext context, final MethodInfo info, final MethodVisitor delegateMethodVisitor) {
        super(Opcodes.ASM5, delegateMethodVisitor);
        this.factory = factory;
        this.context = context;
        this.info = info;
        }
    
   private boolean shouldMutate() {
       if (info.isGeneratedEnumMethod()) {
            return false;
        } else {
            final MutationIdentifier newId = this.context.registerMutation(this.factory, "MathMutatorGroovy");
            return this.context.shouldMutate(newId);
        }
   }
    
    @Override
    public void visitLdcInsn(Object cst) {
        if (shouldMutate()) {
            if (cst.equals("plus")) {
                mv.visitLdcInsn("minus");
            } else if (cst.equals("minus")) {
                mv.visitLdcInsn("plus");
            } else if (cst.equals("div")) {
                mv.visitLdcInsn("multiply");
            } else if (cst.equals("multiply")) {
                mv.visitLdcInsn("div");
            } else {
            mv.visitLdcInsn(cst);
            }
        }
    }
}
