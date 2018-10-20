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

public enum AORMutator4 implements MethodMutatorFactory {

AOR_MUTATOR4;

@Override
public MethodVisitor create(final MutationContext context,
final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
return new AORMethodVisitor4(this, methodInfo, context, methodVisitor);
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

class AORMethodVisitor4 extends AbstractInsnMutator {
    
AORMethodVisitor4(final MethodMutatorFactory factory,
final MethodInfo methodInfo, final MutationContext context,
final MethodVisitor writer) {
super(factory, methodInfo, context, writer);
}
    
private static final Map<Integer, ZeroOperandMutation> MUTATIONS4 = new HashMap<Integer, ZeroOperandMutation>();
    
static
{
    
//integers
MUTATIONS4.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IREM, "Replaced integer addition with modulus"));
MUTATIONS4.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IREM, "Replaced integer subtraction with modulus"));
MUTATIONS4.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IREM,"Replaced integer multiplication with modulus"));
MUTATIONS4.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IREM, "Replaced integer division with modulus"));
MUTATIONS4.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IMUL, "Replaced integer modulus with multiplication"));

//longs
MUTATIONS4.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LREM, "Replaced long addition with modulus"));
MUTATIONS4.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LREM, "Replaced long subtraction with modulus"));
MUTATIONS4.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LREM, "Replaced long division with modulus"));
MUTATIONS4.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LREM, "Replaced long multiplication with modulus"));
MUTATIONS4.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LMUL, "Replaced long modulus with modulus"));
    
//floats
MUTATIONS4.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FREM, "Replaced float addition with multiplication"));
MUTATIONS4.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FREM, "Replaced float subtraction with multiplication"));
MUTATIONS4.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FREM, "Replaced float multiplication with multiplication"));
MUTATIONS4.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FREM, "Replaced float division with multiplication"));
MUTATIONS4.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FMUL, "Replaced float modulus with multiplication"));

//doubles
MUTATIONS4.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DREM, "Replaced double addition with modulus"));
MUTATIONS4.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DREM, "Replaced double subtraction with modulus"));
MUTATIONS4.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DREM, "Replaced double multiplication with modulus"));
MUTATIONS4.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DREM, "Replaced double division with modulus"));
MUTATIONS4.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DREM, "Replaced double modulus with modulus"));

}
    
@Override
protected Map<Integer, ZeroOperandMutation> getMutations() {
return MUTATIONS4;
}
    
}

