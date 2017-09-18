package csc202.finalprjct;

import java.beans.PropertyEditorSupport;

@SuppressWarnings("unchecked")
public class EnumEditor extends PropertyEditorSupport {
    private Class clazz;

    public EnumEditor(Class clazz) {
        this.clazz = clazz;
    }

    public String getAsText() {
        return (getValue() == null ? "" : ((Enum) getValue()).name());
    }

    public void setAsText(String text) throws IllegalArgumentException {
        setValue(Enum.valueOf(clazz, text));
    }
}
