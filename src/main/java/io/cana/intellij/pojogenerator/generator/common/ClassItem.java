package io.cana.intellij.pojogenerator.generator.common;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by vadim on 25.09.16.
 */
public class ClassItem {
    private String annotation;
    private String classAnnotation;
    private String className;
    private String packagePath;
    private Map<String, ClassField> classFields = new LinkedHashMap<String, ClassField>();
    private Set<String> classImports = new HashSet<String>();

    public ClassItem(String className) {
        this.className = className;
    }

    public void addClassField(String name, ClassField classType) {
        classFields.put(name, classType);
    }

    public String getClassName() {
        return className;
    }

    public void addClassImport(String value) {
        classImports.add(value);
    }

    public Map<String, ClassField> getClassFields() {
        return classFields;
    }

    public Set<String> getClassImports() {
        return classImports;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getClassAnnotation() {
        return classAnnotation;
    }

    public void setClassAnnotation(String classAnnotation) {
        this.classAnnotation = classAnnotation;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }
}
