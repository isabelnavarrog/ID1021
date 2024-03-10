my_csv=readtable('trains.csv'); %Reading the data

disp(my_csv); % Displaying the data just to confirm 

fileName = 'names.csv';
% my sample text contains ---> apple, baseball, car, donut, & elephant in single column.
FID = fopen(fileName);
data = textscan(FID,'%s');
fclose(FID);
stringData = string(data{:});

% Creating a path for the headers
Cities_org = my_csv(:,1);
Cities_dest =my_csv(:,2);
Time =my_csv(:,3);
s = [1,2,2,3,4,5,6,7,8,9,10,11,12,13,14,15,14,15,16,12,17,17,7,18,8,19,20,21,3,22,23,23,24,25,1,1,26,27,28,29,27,28,30,31,32,31,33,34,35,36,26,37,38,27,37,37,37,39,40,40,39,41,42,43,43,43,44,45,45,46,46,4,47,13,9];  
t = [2,3,22,4,5,6,7,8,9,52,11,12,13,9,15,10,16,16,21,17,7,6,18,48,19,20,18,6,22,23,24,14,25,42,42,26,27,28,29,36,29,30,31,32,51,50,34,35,36,32,49,38,33,38,49,33,39,40,24,41,41,33,43,2,22,25,45,10,46,10,15,48,13,8,47]; 
A = table2array(Time); 
G = graph(s,t,A);
G.Nodes.Name=stringData;
G.Edges.Name = Time;
p = plot(G);
G.Nodes.NodeColors = degree(G);
p.NodeCData = G.Nodes.NodeColors;
colorbar
P=shortestpath(G, 1,29);
for i=1:75
    labeledge(p,s(1,i),t(1,i),A(i,1));
end
