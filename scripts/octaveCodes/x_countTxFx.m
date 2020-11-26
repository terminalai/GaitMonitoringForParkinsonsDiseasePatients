% Count the true pos, false pos, etc in frames
%
% gtframe: column vector of ground truth of frame
% lframe: column vector classification result of frame
% offDelay/onDelay: tolerance for latency in the algorithm. Specified in frames.
%
% The latency-tolerance can only be used with binary labels : 0=nothing, 1=event
%
% Returns: [TP TN FP FN Nev]
% Nev: number of events in the ground truth data
% 

function res=x_countTxFx(gtframe,lframe,offDelay,onDelay)

    % Want here to create labels tolerating algorithm latency in the
    % transitions from nothing->event and event->nothing. 
    % For this we need gtframedelayoff and grframedelayon that are 
    % variants of gtframe with delay.
    % This is built using a help 'labels' array.
    
    % Convert the frame labels to the format: [fromsample tosample]
    f = find(gtframe(2:end)-gtframe(1:end-1));
    % add a discontinuity at the start and end
    f=[0; f; size(gtframe,1)];
    % convert
    labels=[];          % [fromframe toframe] where there is an event
    for li=1:size(f,1)-1
        if gtframe(f(li)+1) == 1
            labels = [labels; f(li)+1 f(li+1)];
        end
    end    
    
    % Labels for the delay
    gtframedelayoff  = zeros(size(gtframe,1),1);
    gtframedelayon   = zeros(size(gtframe,1),1);
    s = [1:size(gtframe,1)];                           % s: 1, 2, ..., frame number
    for li=1:size(labels,1)
        s_index = find(s>=labels(li,1),1,'first');       
        e_index = find(s<=labels(li,2),1,'last');
        % reference vectors with Off delay
        e_indexOff = find(s<=labels(li,2)+offDelay,1,'last');
        gtframedelayoff(s_index:e_indexOff) = 1;

        % reference vectors with On delay
        s_indexOn = find(s>=(labels(li,1)+onDelay),1,'first');       
        gtframedelayon(s_indexOn:e_index) = 1;
        %[s_index e_index s_indexOn e_indexOff lt]
        %[s_index e_index s_indexOn lt]
        
    end
%     
%     figure(7); clf;
%     subplot(4,1,1); plot(gtframe);
%     subplot(4,1,2); plot(gtframedelayon);
%     subplot(4,1,3); plot(gtframedelayoff);
%     subplot(4,1,4); plot(lframe);
%     linkaxes
%        
    res_vec = zeros( size(gtframe,1),6); % TP TPd TN TNd FP FN
     
    % mark all correct detected (TP) and not detected (TN) time-slots
    i_TX = find( gtframe == lframe); % all correct time-slots

    i_TP = find( lframe(i_TX) == 1 ); % correct detected 
    res_vec( i_TX(i_TP),1 ) = 1;

    i_TN =  find( lframe(i_TX) == 0 ); % correct not detected
    res_vec( i_TX(i_TN),3 ) = 1;
 

    % mark all false detected (FP) and missed (FN) time-slots
    i_FX = find( gtframe ~= lframe ); % all wrong time-slots

    i_FP = find( lframe(i_FX) == 1 ); % wrong detected
    res_vec( i_FX(i_FP),5 ) = 1;

    i_FN = find( lframe(i_FX) == 0 ); % missed 
    res_vec( i_FX(i_FN),6 ) = 1;


    % TPd : time-slots true due to the off delay
    i_X = find( res_vec(:,5) == gtframedelayoff );
    i_TPd = find( res_vec(i_X,5) == 1 );
    res_vec( i_X(i_TPd),2 ) = 1;
    res_vec( i_X(i_TPd),5 ) = 0;

    % TNd : time-slots true due to the on delay
    i_X = find( res_vec(:,6) ~= gtframedelayon );
    i_TNd = find( res_vec(i_X,6) == 1 );
    res_vec( i_X(i_TNd),4 ) = 1;
    res_vec( i_X(i_TNd),6 ) = 0;

    
    % sum up result 
     TP = sum(res_vec(:,1)) + sum(res_vec(:,2));
     TN = sum(res_vec(:,3)) + sum(res_vec(:,4));
     FP = sum(res_vec(:,5));
     FN = sum(res_vec(:,6));
     
res=[TP TN FP FN size(labels,1)];
