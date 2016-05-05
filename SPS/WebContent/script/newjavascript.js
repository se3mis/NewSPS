/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



var myTable = document.getElementById('merge');

var rows = myTable.getElementsByTagName('tr');

var numRows = rows.length;

var numRowSpan=1;


for (var j = 1; j <(numRows-1); j++) {

if (numRowSpan<=1) {

var currentRow = myTable.getElementsByTagName('tr')[j];

var currentCell= currentRow.getElementsByTagName('td')[0];  // the 1st column

var currentCellData = currentCell.childNodes[0].data;

}

if (j<numRows-1) {

if (myTable.getElementsByTagName('tr')[j+1]) {

var nextRow = myTable.getElementsByTagName('tr')[j+1];

var nextCell = nextRow.getElementsByTagName('td')[0];

var nextCellData = nextCell.childNodes[0].data;


// compare the current cell and the next cell

if (currentCellData == nextCellData) {

numRowSpan += 1;

currentCell.rowSpan = numRowSpan;

nextCell.style.display = 'none';   //disappear the next cell

} else {

numRowSpan = 1;

}

}

}

}




