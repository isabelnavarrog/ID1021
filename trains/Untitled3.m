my_csv=readtable('trains.csv'); %Reading the data

disp(my_csv); % Displaying the data just to confirm 

% Creating a path for the headers
Cities_org = my_csv(:,1);
Cities_dest =my_csv(:,2);
Time =my_csv(:,3);




shg()