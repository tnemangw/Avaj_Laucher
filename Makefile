all: clean
	echo "comment" > sources.txt
	find . -depth -name "*.java" >> sources.txt #
	javac -d . -sourcepath @sources.txt #
	# java avaj.simulator.Simulator scenario #
clean:
	rm -rf avaj
	rm -rf out
	rm -rf sources.txt
	rm -rf simulation.txt
	find . -depth -name "*.class" -delete