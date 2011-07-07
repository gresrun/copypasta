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

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.Resource;

public final class Main
{
	public static void main(final String... args)
	throws Exception
	{
		if (args.length != 1)
		{
			throw new IllegalArgumentException("USAGE: java -jar copypasta.jar <port>");
		}
		final int port = Integer.parseInt(args[0]);
		PortJavaScriptHttpServlet.PORT.set(port);
		final Server server = new Server(port);
		final ServletContextHandler servletHandler = new ServletContextHandler();
		servletHandler.setContextPath("/");
		servletHandler.setServer(server);
		servletHandler.addServlet(CopyPastaWebSocketServlet.class, "/CopyPastaWS/*");
		servletHandler.addServlet(PortJavaScriptHttpServlet.class, "/js/port.js");
		final ResourceHandler resHandler = new ResourceHandler();
		resHandler.setBaseResource(Resource.newSystemResource("/webapp/"));
		final HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{servletHandler, resHandler, new DefaultHandler()});
        server.setHandler(handlers);
		server.start();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					server.stop();
					server.join();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}, "CopyPastaShutdown"));
	}

	private Main(){}
}
