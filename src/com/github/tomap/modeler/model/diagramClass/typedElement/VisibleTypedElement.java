package com.github.tomap.modeler.model.diagramClass.typedElement;

import com.github.tomap.modeler.model.diagramClass.type.Type;
import com.github.tomap.modeler.model.diagramClass.visibility.Visibility;

public abstract class VisibleTypedElement extends TypedElement{

	protected boolean isStatic;
	protected Visibility visibility;
	
	public VisibleTypedElement(String name, boolean isFinal, Type type, boolean isStatic, Visibility visibility) {
		super(name, isFinal, type);
		this.isStatic = isStatic;
		this.visibility = visibility;
	}

}
