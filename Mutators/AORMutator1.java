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

public enum AORMutator1 implements MethodMutatorFactory {

AOR_MUTATOR1;

@Override
public MethodVisitor create(final MutationContext context,
final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
return new AORMethodVisitor1(this, methodInfo, context, methodVisitor);
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

class AORMethodVisitor1 extends AbstractInsnMutator {

AORMethodVisitor1(final MethodMutatorFactory factory,
final MethodInfo methodInfo, final MutationContext context,
final MethodVisitor writer) {
super(factory, methodInfo, context, writer);
}

private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<>();

static
{
    
//integers
MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.ISUB, "Replaced integer addition with subtraction"));
MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IADD, "Replaced integer subtraction with addition"));
MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.ISUB, "Replaced integer multiplication with subtraction"));
MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.ISUB, "Replaced integer division with subtraction"));
MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.ISUB, "Replaced integer modulus with subtraction"));

// longs
MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LSUB, "Replaced long addition with subtraction"));
MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LADD, "Replaced long subtraction with addition"));
MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LADD, "Replaced long division with addition"));
MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LADD, "Replaced long multiplication with addition"));
MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LADD, "Replaced long modulus with addition"));
    
//floats
MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FSUB, "Replaced float addition with subtraction"));
MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FADD, "Replaced float subtraction with addition"));
MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FSUB, "Replaced float multiplication with subtraction"));
MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FSUB, "Replaced float division with subtraction"));
MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FSUB, "Replaced float modulus with subtraction"));

// doubles
MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DSUB, "Replaced double addition with subtraction"));
MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DADD, "Replaced double subtraction with addition"));
MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DSUB, "Replaced double multiplication with subtraction"));
MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DSUB, "Replaced double division with subtraction"));
MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DSUB, "Replaced double modulus with subtraction"));

}

@Override
protected Map<Integer, ZeroOperandMutation> getMutations() {
return MUTATIONS;
}

}
