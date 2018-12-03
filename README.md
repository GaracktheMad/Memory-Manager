# Memory-Manager
## Usage:
### Adding Processes:
1. Select a Process. There are a set number of processes that can be in memory at a time, and are generated automatically in the selection combo box.
2. Select a fit algorithm. 
3. Click the add button.
  * Processes added successfully will be displayed on the memory panel.
  * Unsuccessful processes will either display an error, or will appear on the waiting queue.
  * The process will disappear from the waiting queue when added to memory successfully or when compaction is requested and space is available.

### Removing Processes:
1. Select a Process already in memory. This will display the size of the process and enable the remove button.
2. Click remove.

### Miscellaneous:
+ You can compact memory with the compact button.
+ You can resize memory with some predefined options. Doing this will wipe all of memory as well.
+ You can reset the program to its default size and clear all elements with the "Clear All" button.

## Warnings:
### Information:
* Sizes must be integer values
  * This also means sizes must be less than the max value of an int in Java
  * Negative values will be turned into their absolute value
* While the program doesn't display it, the sizes are all implied to be in "KB"
### Quirks:
* Processes with small sizes may not properly display as contiguous. 
  * This issue occurs due to the labels attached to the rectangle.
  * An empty space has a gray color and a process has a cyan color to compensate. All of the colored rectangles should meet but may not due to java problems with small sizes.
### Bugs/Faults:
At present, there are no bugs or faults.