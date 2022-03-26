package com.N26.PetStore.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtils {

	static String ReportResultsFolder = System.getProperty("user.dir") + "/TestReport";
	
	public static void prepare_Setup_to_rerun() {

		File ResultsFolder = new File(ReportResultsFolder);
		try {
			deleteResultsFolder(ResultsFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			createResultsFolder(ResultsFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createResultsFolder(File ResultsFolder) throws IOException {

		if (!ResultsFolder.exists()) {
			if (ResultsFolder.mkdir()) {
				Log.INFO("Directory is created!");
			} else {
				Log.ERROR("Failed to create directory!");
			}
		}
	}

	public static void deleteResultsFolder(File ResultsFolder) throws IOException {

		if (ResultsFolder.isDirectory()) {

			if (ResultsFolder.list().length == 0) {

				ResultsFolder.delete();
				Log.INFO("Folder is deleted : " + ResultsFolder.getAbsolutePath());

			} else {

				String files[] = ResultsFolder.list();

				for (String temp : files) {

					File fileDelete = new File(ResultsFolder, temp);

					deleteResultsFolder(fileDelete);
				}

				if (ResultsFolder.list().length == 0) {
					ResultsFolder.delete();
					Log.INFO("Directory is deleted : " + ResultsFolder.getAbsolutePath());
				}
			}

		} else {
			ResultsFolder.delete();
			Log.INFO("File is deleted : " + ResultsFolder.getAbsolutePath());
		}
	}
	
}

//Prangya Paramita Panda
