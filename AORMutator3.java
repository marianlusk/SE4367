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
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;

public enum AORMutator3 implements MethodMutatorFactory {

AOR_MUTATOR3;

@Override
public MethodVisitor create(final MutationContext context,
final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
return new AORMethodVisitor3(this, methodInfo, context, methodVisitor);
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

class AORMethodVisitor3 extends AbstractInsnMutator {
    
AORMethodVisitor3(final MethodMutatorFactory factory,
final MethodInfo methodInfo, final MutationContext context,
final MethodVisitor writer) {
super(factory, methodInfo, context, writer);
}
    
private static final Map<Integer, ZeroOperandMutation> MUTATIONS3 = new HashMap<>();
    
static
{

//integers
MUTATIONS3.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IDIV, "Replaced integer addition with division"));
MUTATIONS3.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IMUL, "Replaced integer subtraction with multiplication"));
MUTATIONS3.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IDIV, "Replaced integer multiplication with division"));
MUTATIONS3.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IMUL, "Replaced integer division with multiplication"));
MUTATIONS3.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IDIV, "Replaced integer modulus with division"));

//longs
MUTATIONS3.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LMUL, "Replaced long addition with multiplication"));
MUTATIONS3.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LMUL, "Replaced long subtraction with multiplication"));
MUTATIONS3.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LMUL, "Replaced long division with multiplication"));
MUTATIONS3.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LDIV, "Replaced long multiplication with division"));
MUTATIONS3.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LDIV, "Replaced long modulus with division"));

//floats
MUTATIONS3.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FMUL, "Replaced float addition with multiplication"));
MUTATIONS3.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FMUL, "Replaced float subtraction with multiplication"));
MUTATIONS3.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FDIV, "Replaced float multiplication with division"));
MUTATIONS3.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FMUL, "Replaced float division with multiplication"));
MUTATIONS3.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FDIV, "Replaced float modulus with division"));
    
//doubles
MUTATIONS3.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DMUL, "Replaced double addition with multiplication"));
MUTATIONS3.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DMUL, "Replaced double subtraction with multiplication"));
MUTATIONS3.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DDIV, "Replaced double multiplication with division"));
MUTATIONS3.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DMUL, "Replaced double division with multiplication"));
MUTATIONS3.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DDIV, "Replaced double modulus with division"));
}
    
@Override
protected Map<Integer, ZeroOperandMutation> getMutations() {
return MUTATIONS3;
}
    
}

