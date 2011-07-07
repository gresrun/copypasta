/*
 * Copyright 2011 Greg Haines
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.greghaines.copypasta;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PortJavaScriptHttpServlet extends HttpServlet
{
	public static final AtomicInteger PORT = new AtomicInteger(8080);
	
	private static final long serialVersionUID = -1313178658301999702L;
	private static final String hostname;

	static
	{
		try
		{
			hostname = InetAddress.getLocalHost().getHostName();
		}
		catch (UnknownHostException uhe)
		{
			throw new RuntimeException(uhe);
		}
	}
	
	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
	throws ServletException, IOException
	{
		final String script = "var port = " + PORT.get() + ";\nvar host = \"" + hostname + "\";\n";
		resp.setContentType("text/javascript");
		resp.setStatus(HttpServletResponse.SC_OK);
		final OutputStream os = resp.getOutputStream();
		os.write(script.getBytes(Charset.forName("UTF-8")));
		os.flush();
		os.close();
	}
}
