//
// SwingTextFieldWidget.java
//

/*
ImageJ software for multidimensional image processing and analysis.

Copyright (c) 2010, ImageJDev.org.
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the names of the ImageJDev.org developers nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
*/

package imagej.plugin.ui.swing;

import imagej.plugin.ui.ParamModel;
import imagej.plugin.ui.TextFieldWidget;

import java.awt.BorderLayout;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Swing implementation of text field widget.
 * 
 * @author Curtis Rueden
 */
public class SwingTextFieldWidget extends SwingInputWidget
	implements DocumentListener, TextFieldWidget
{

	private final JTextField textField;

	public SwingTextFieldWidget(final ParamModel model, final int columns) {
		super(model);

		textField = new JTextField("", columns);
		add(textField, BorderLayout.CENTER);
		textField.getDocument().addDocumentListener(this);

		refresh();
	}

	// -- DocumentListener methods --

	@Override
	public void changedUpdate(final DocumentEvent e) {
		documentUpdate();
	}

	@Override
	public void insertUpdate(final DocumentEvent e) {
		documentUpdate();
	}

	@Override
	public void removeUpdate(final DocumentEvent e) {
		documentUpdate();
	}

	// -- TextFieldWidget methods --

	@Override
	public String getText() {
		return textField.getText();
	}

	// -- InputWidget methods --

	@Override
	public void refresh() {
		textField.setText(model.getValue().toString());
	}

	// -- Helper methods --

	private void documentUpdate() {
		model.setValue(textField.getText());
	}

}
