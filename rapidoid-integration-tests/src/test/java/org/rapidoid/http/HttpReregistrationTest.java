package org.rapidoid.http;

/*
 * #%L
 * rapidoid-integration-tests
 * %%
 * Copyright (C) 2014 - 2016 Nikolche Mihajlovski and contributors
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

import org.junit.Test;
import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.GET;
import org.rapidoid.annotation.POST;
import org.rapidoid.annotation.Since;
import org.rapidoid.setup.On;

@Authors("Nikolche Mihajlovski")
@Since("5.1.0")
public class HttpReregistrationTest extends HttpTestCommons {

	@Test
	public void testControllerDeregistration() {
		Object ctrl1 = ctrl1("next");
		Object ctrl2 = ctrl2();

		notFound("/inc");
		notFound("/dec");

		On.beans(ctrl1);

		onlyGet("/inc?x=5");
		notFound("/dec");

		On.deregister(ctrl1);
		On.beans(ctrl2);

		onlyPost("/dec?x=12");
		notFound("/inc");

		On.deregister(ctrl2.getClass());

		notFound("/inc");
		notFound("/dec");
	}

	@Test
	public void testControllerReregistration() {
		notFound("/inc");
		notFound("/dec");

		On.beans(ctrl1("nextA"));
		onlyGet("/inc?x=100");

		On.beans(ctrl1("nextB"));
		onlyGet("/inc?x=200");

		On.beans(ctrl1("nextC"));
		onlyGet("/inc?x=300");

		// can deregister with other instance, only the class matters for deregistration, not the instance
		On.deregister(ctrl1("invisible"));

		notFound("/inc");
		notFound("/dec");
	}

	@Test
	public void testLambdaDeregistration() {
		notFound("/foo");

		On.page("/foo").gui((Req req, Integer x) -> req.data() + ":" + x);

		getAndPost("/foo?a=12&x=3");

		On.deregister("GET,POST", "/foo");

		notFound("/foo");
	}

	private Object ctrl1(String prefix) {
		class AbcCtrl {
			@GET
			String inc(Integer x) {
				return prefix + (x + 1);
			}
		}

		return new AbcCtrl();
	}

	private Object ctrl2() {
		class AbcCtrl {
			@POST
			int dec(Integer x) {
				return x - 1;
			}
		}

		return new AbcCtrl();
	}

}