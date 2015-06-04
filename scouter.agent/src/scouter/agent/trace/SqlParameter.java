/*
 *  Copyright 2015 LG CNS.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 */

package scouter.agent.trace;

public class SqlParameter {
	private final static int MAX_SIZE = 128;
	protected int count = 0;
	protected String[] entry = new String[MAX_SIZE];
	protected String sql;

	public SqlParameter() {
	}

	public SqlParameter(String sql) {
		setSql(sql);
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public void put(int x, String value) {
		if (MAX_SIZE <= x)
			return;
		entry[x] = value;
		if (x >= count) {
			count = x + 1;
		}
	}

	public String get(int x) {
		if (0 <= x && x < count)
			return entry[x];
		else
			return null;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < count; i++) {
			if (entry[i] != null) {
				if (buf.length() > 0) {
					buf.append(",");
				}
				buf.append(entry[i]);
			}
		}
		return buf.toString();
	}

	public String toString(String prev) {
		StringBuffer buf = new StringBuffer();
		if (prev != null) {
			buf.append(prev);
			for (int i = 0; i < count; i++) {
				if (entry[i] != null) {
					buf.append(",");
					buf.append(entry[i]);
				}
			}
		} else {
			for (int i = 0; i < count; i++) {
				if (entry[i] != null) {
					if (buf.length() > 0) {
						buf.append(",");
					}
					buf.append(entry[i]);
				}
			}
		}
		return buf.toString();
	}

	public synchronized void clear() {
		for (int i = 0; i < count; i++) {
			entry[i]=null;
		}
		count = 0;
	}

}