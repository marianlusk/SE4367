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
// Edit my Marian Lusk & Mehrnoush Sotoudeh
// SE 4367 Term Project Phase 1

package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum RORMutator2 implements MethodMutatorFactory {

ROR_MUTATOR2;

@Override
public MethodVisitor create(final MutationContext context,
final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
return new RORMethodVisitor2(this, context, methodVisitor);
}

@Override
public String getGloballyUniqueId() {
return this.getClass().getName();
}

@Override
public String getName() {
return name();
}

}

class RORMethodVisitor2 extends AbstractJumpMutator {
    
    private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<>();
    
    static
    {
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFGT, "Changed less than equal to greater than"));
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFLT, "Changed greater than equal to less than"));
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLE, "Changed greater than to less than equal"));
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFGE, "Changed less than to greater than equal"));
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFLT, "Changed equal to less than"));
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFLT, "Changed not equal to less than"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPGT, "Changed less than equal to great than"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPLT, "Changed greater than equal to less than"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPLT, "Changed greater than to less than"));
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPGT, "Changed less than to greater than"));
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPGE, "Changed equal to greater than equal"));
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPLT, "Changed not equal to less than"));

        
    }
    
    RORMethodVisitor2(final MethodMutatorFactory factory,
                            final MutationContext context, final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }
    
    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
    
}
