function a_out = idft_fir( a_in, offset )

% pulse_response = idft_fir( spectrum_in, phase_offset )
%
% FUNCTION TO GENERATE A LTI IMPULSE RESPONSE FROM 
%       A MAGNITUDE RESPONSE (a_in)
%
%  OPTIONAL PARAMETER PHASE OFFSET (offset) defaults to zero.
%    offset can be a scalar or size(a_in) for designer phase 
%    variance

if nargin<2
    offset = 0;
end

N = length(a_in)*2 - 1;

linear_phase = [ (-2*pi/N)*[0:(N-1)/2]*( (N-1)/2 ) ];

phase_offset = (offset.*N);

half_wave = a_in.* exp(1i*(phase_offset+linear_phase) ); 
half_wave(1) = a_in(1);

full_wave = [half_wave,conj(half_wave(end:-1:2))];

a_out = real( ifft( full_wave ) );

return