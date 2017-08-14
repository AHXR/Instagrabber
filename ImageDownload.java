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
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * ImageDownload is a very small class that simply downloads the image from Instagram.
 * 
 * In URLControl.java, there is a disabled function called "getExtension".
 * The function used to be active because I wasn't sure how I completely felt about some
 * file formatted Instagram pictures. I've come to realize that all(?) or most of the files
 * uploaded on Instagram are converted to jpg. Therefore the function was useless to me.
 * 
 * I might be wrong. Time will tell.
 * 
 * @author AR
 * @version 1.0
 * 
 * public static boolean downloadImage( String URL, String Location )
 */

public class ImageDownload {
	
	/*
	 * Here is the image downloading function. It is only called in SwingGUI.java.
	 */
	public static boolean downloadImage( String URL, String Location ) {
		
		/*
		 * Once the URL is cleaned out (URLControl.java), I added the URL redirection link
		 * that will bring me to the raw file. For the sake of quality purposes, I made it so
		 * the function will always download the largest version of the picture, hence "size=l"
		 * 
		 * As you can see from the string itself, this is why cleanURL was removing any extra stuff
		 * hanging from the URL.
		 */
		String 
			s_new_url = URLControl.cleanURL( URL ) + "/media?size=l";
		
		// Simply opening up the URL and downloading the file.
		try(InputStream in = new URL( s_new_url ).openStream()){
		    Files.copy(in, Paths.get( Location + ".jpg" ) );
		    return true;
		    
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}	
}
