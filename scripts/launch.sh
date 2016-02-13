#!/bin/bash

OUT_FOLDER="$(pwd -P)/../out"

usage() {
	echo "=========================="
	echo "====                 ====="
	echo "====     Welcome !   ====="
	echo "====                 ====="
	echo "=========================="
	echo "\nUse this launcher to compile and launch the SnowBall game\n"


	echo "Usage: launch.sh [ -b <target> ] [ -l ]"
	echo "\t-l: Launch the application."
	echo "\t    Print an error if the project hasn't been build before."
	echo "\t-b <target>: build the project with the specified ant target."
	echo "\t\tAvailable targets: clean, compile, jar"
}

parseArgs() {
	while getopts "lb:" opt; do
		case $opt in
			l) 
				launchApp
				;;
			b)	
				ant $OPTARG
				;;
			h)
				usage
				;;
			*)
				echo "Argument not recognized."
				usage
				;;
		esac
	done
}

launchApp() {
	if [[ ! -d ${OUT_FOLDER} ]]; then
		echo "JAR Folder not found. Please use ./launch.sh -b jar"
	else
		java -classpath "${OUT_FOLDER}/SnowBall.jar" launch.Main
	fi
}

parseArgs "$@"