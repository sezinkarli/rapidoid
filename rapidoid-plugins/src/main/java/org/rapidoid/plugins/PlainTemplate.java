package org.rapidoid.plugins;

/*
 * #%L
 * rapidoid-plugins
 * %%
 * Copyright (C) 2014 - 2015 Nikolche Mihajlovski and contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.IOException;
import java.io.OutputStream;

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.log.Log;
import org.rapidoid.plugins.templates.ITemplate;
import org.rapidoid.u.U;

@Authors("Nikolche Mihajlovski")
@Since("4.1.0")
public class PlainTemplate implements ITemplate {

	private final String content;

	public PlainTemplate(String content) {
		this.content = content;
	}

	@Override
	public void render(OutputStream output, Object... scopes) {
		Log.warn("Using the plain template!");
		try {
			output.write(content.getBytes());
		} catch (IOException e) {
			throw U.rte(e);
		}
	}

	@Override
	public String render(Object... scopes) {
		return content;
	}

}
