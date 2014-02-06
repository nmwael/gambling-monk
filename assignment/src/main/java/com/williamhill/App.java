package com.williamhill;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 * 
 */
public class App {
	public static final String NOT_EXISTING_DIRECTORY_HELP= "Not existing directory >";
	public static final String NOT_A_VALID_DIRECTORY_ERROR = "Not a valid directory >";
	public static final String NO_FILES_FOUND_HELP= "Sorry no files found";
	public static final String CURRENT_VALID_ARGUMENTS_HELP = "-f \"pom.xml\" \"<directory>\"";
	public static final String VALID_ARGUMENTS_HELP = "Please provide 3 arguments, valid arguments are:";

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out
					.println(VALID_ARGUMENTS_HELP);
			System.out.println(CURRENT_VALID_ARGUMENTS_HELP);
		} else {
			String fileFlagArgument = args[0];
			String fileNameArgument = args[1];
			String directoryArgument = args[2];

			File directory = new File(directoryArgument);
			if (directory.exists()) {
				if (directory.isDirectory()) {

					List<File> filesFound = findFiles(directory,
							fileNameArgument);
					
					for (File file : filesFound) {
						System.out.println(file.getAbsolutePath());

					}
					if(filesFound.isEmpty()){
						System.out.println(NO_FILES_FOUND_HELP);

					}
				}
				else{
					System.out.println(NOT_A_VALID_DIRECTORY_ERROR
							+ directory.getAbsolutePath() + "<");

				}
			} else {
				System.out.println(NOT_EXISTING_DIRECTORY_HELP
						+ directory.getAbsolutePath() + "<");

			}
		}

	}

	/**
	 * grabbed from stackoverflow, modified for assignment
	 * 
	 * @param dir
	 * @param fileName
	 * @return
	 */
	private static List<File> findFiles(File dir, String fileName) {
		ArrayList<File> result = new ArrayList<>(); // no need to store result
													// as String, you're
													// returning File anyway
		File[] dirlist = dir.listFiles();

		for (int i = 0; i < dirlist.length; i++) {
			if (dirlist[i].isDirectory()) {
				result.addAll(findFiles(dirlist[i], fileName));
				if (!result.isEmpty())
					break; // recursive call found the file; terminate the loop
			} else if (dirlist[i].getName().matches(fileName)) {
				result.add(dirlist[i]); // found the file; adding it
			}
		}
		return result; // will return empty list if we didn't find anything
	}
}
