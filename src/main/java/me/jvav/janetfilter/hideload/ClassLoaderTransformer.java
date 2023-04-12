package me.jvav.janetfilter.hideload;

import com.janetfilter.core.plugin.MyTransformer;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.tree.*;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class ClassLoaderTransformer implements MyTransformer {
    @Override
    public String getHookClassName() {
        return "java/lang/ClassLoader";
    }

    @Override
    public byte[] transform(String className, byte[] classBytes, int order) throws Exception {
        ClassReader reader = new ClassReader(classBytes);
        ClassNode node = new ClassNode(ASM5);
        reader.accept(node, 0);

        for (MethodNode mn : node.methods) {
            if ("checkName".equals(mn.name)) {
                InsnList insnList = new InsnList();
                insnList.add(new VarInsnNode(ALOAD, 0));
                insnList.add(new MethodInsnNode(INVOKESTATIC, "me/jvav/janetfilter/hideload/ClassLoaderFilter", "testClass", "(Ljava/lang/String;)V", false));
                mn.instructions.insert(insnList);
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        node.accept(writer);

        return writer.toByteArray();
    }
}
