/*
 * Instagrabber: Download Instagram Pictures
 * Copyright (C) 2017 AR.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

// Imports.
package instagrabber.global;

/*
 * GlobalMessages is an enumeration that simply holds any dialog messages that 
 * will show up to the user. This was done just so I can edit the messages without
 * going through the whole code. 
 * 
 * @author AR
 * @version 1.0
 */
public enum GlobalMessages {
	DIALOGSAVE	("Select a directory and file name to save the Instagram picture: "),
	CANCEL		("The Instagram picture will not be downloaded."),
	EXAMPLE		("Example: https://www.instagram.com/p/BXlplv-F3t1"),
	SUCCESS		("The Instagram picture has been downloaded!"),
	FAILURE		("There was an error downloading the Instagram picture."),
	INVALID_URL ("That doesn't seem to be a working URL.");
	
	private String 
		msg;

	GlobalMessages( String v ) { this.msg = v; }
	public String value( ) { return msg; }

}
