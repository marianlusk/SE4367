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

public enum AORMutator2 implements MethodMutatorFactory {

AOR_MUTATOR2;

@Override
public MethodVisitor create(final MutationContext context,
final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
return new AORMethodVisitor2(this, methodInfo, context, methodVisitor);
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

class AORMethodVisitor2 extends AbstractInsnMutator {
    
AORMethodVisitor2(final MethodMutatorFactory factory,
final MethodInfo methodInfo, final MutationContext context,
final MethodVisitor writer) {
super(factory, methodInfo, context, writer);
}
    
private static final Map<Integer, ZeroOperandMutation> MUTATIONS2 = new HashMap<>();
    
static
{
//integers
MUTATIONS2.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IMUL, "Replaced integer addition with multiplication"));
MUTATIONS2.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IDIV, "Replaced integer subtraction with division"));
MUTATIONS2.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IADD, "Replaced integer multiplication with addition"));
MUTATIONS2.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IADD, "Replaced integer division with addition"));
MUTATIONS2.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IADD, "Replaced integer modulus with addition"));

//longs
MUTATIONS2.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LDIV, "Replaced long addition with division"));
MUTATIONS2.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LDIV, "Replaced long subtraction with division"));
MUTATIONS2.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LSUB, "Replaced long division with subtraction"));
MUTATIONS2.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LSUB, "Replaced long multiplication with subtraction"));
MUTATIONS2.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LSUB, "Replaced long modulus with subtraction"));
    
//floats
MUTATIONS2.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FDIV, "Replaced float addition with division"));
MUTATIONS2.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FDIV, "Replaced float subtraction with division"));
MUTATIONS2.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FADD, "Replaced float multiplication with addition"));
MUTATIONS2.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FADD, "Replaced float division with addition"));
MUTATIONS2.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FADD, "Replaced float modulus with addition"));
    
//doubles
MUTATIONS2.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DDIV, "Replaced double addition with division"));
MUTATIONS2.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DDIV, "Replaced double subtraction with division"));
MUTATIONS2.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DADD, "Replaced double multiplication with addition"));
MUTATIONS2.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DADD, "Replaced double division with addition"));
MUTATIONS2.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DADD, "Replaced double modulus with addition"));
}
    
@Override
protected Map<Integer, ZeroOperandMutation> getMutations() {
return MUTATIONS2;
}
    
}

