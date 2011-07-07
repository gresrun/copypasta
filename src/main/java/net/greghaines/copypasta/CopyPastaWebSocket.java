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

import java.util.Set;

import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

public class CopyPastaWebSocket implements OnTextMessage
{
	private final Set<CopyPastaWebSocket> users;
	private volatile Connection connection;
	
	public CopyPastaWebSocket(final Set<CopyPastaWebSocket> users)
	{
		this.users = users;
	}

	public void onMessage(final String data)
	{
		for (final CopyPastaWebSocket user : this.users)
		{
			if (user != this)
			{
				try
				{
					user.connection.sendMessage(data);
				}
				catch (Exception e){}
			}
		}
	}

	public void onOpen(final Connection connection)
	{
		this.connection = connection;
		this.users.add(this);
	}

	public void onClose(final int closeCode, final String message)
	{
		this.users.remove(this);
		this.connection = null;
	}
}
