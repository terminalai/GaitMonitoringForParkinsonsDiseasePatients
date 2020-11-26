function i = x_numericalIntegration(x,SR)
%
% Do numerical integration of x with the sampling rate SR
% -------------------------------------------------------------------
% Copyright 2008 Marc Bächlin, ETH Zurich, Wearable Computing Lab.
%
% -------------------------------------------------------------------
i = (sum(x(2:end))/SR+sum(x(1:end-1))/SR)/2;
