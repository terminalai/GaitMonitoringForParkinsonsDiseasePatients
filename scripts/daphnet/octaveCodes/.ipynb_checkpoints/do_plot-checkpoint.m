function do_plot(file)
    data = load(file);
%    clear;
 %   data = load('data/S01R01.txt');

    % Colors of the patches. 
    % 1st entry: not part of experiment
    % 2nd entry: experiment but not freeze
    % 3rd entry: freeze
    pcol = {[1 1 1],[0 .75 0],'r'};
    
    
    
    yltext={'X','Y','Z'};
    ttext={'sensor ankle','sensor knee', 'sensor hip'};
    
    clf;
    subplot(3,3,1);
    %subplot(3,1,1);
    sensorpos=0;
    for sensorpos = 0:2
        for sensoraxis = 0:2
            subplot(3,3,1+sensoraxis*3 + sensorpos);
            %subplot(3,1,1+sensoraxis);
            
            % Plot the patches: find the discontinuities in the labels
            f = find(data(2:end,11)-data(1:end-1,11));
            % add a discontinuity at the start and end
            f=[0; f; size(data,1)];

            
            
            % iterate the discontinuities
            for i=1:size(f,1)-1
                x1 = data(f(i)+1,1)/1000;           % Time of start in ms 
                x2 = data(f(i+1),1)/1000;           % Time of end in ms
                type = data(f(i)+1,11);
                y1 = -3500;
                y2 = -3000;
                
                %if type~=2
%                    continue;
 %               end
                
                patch([x1,x2,x2,x1],[y1 y1 y2 y2],pcol{1+type});
                %fprintf(1,'Patch type %d in [%d-%d]\n',type,x1,x2);
                
            end

            hold on;
            
            plot(data(:,1)/1000,data(:,2+sensorpos*3+sensoraxis));
            
            a=axis;
            a(1)=data(1,1)/1000;
            a(2)=data(end,1)/1000;
            a(3)=-3500;
            a(4)=+3000;
            axis(a);
            
            xlabel('time [s]');
            ylabel(['Acc ' yltext{1+sensoraxis} '[mg]']);
            title(ttext{1+sensorpos});
            
            
            
        end
    end
    
%    linkaxes
    
    
    

end
