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

                                        % TOTAL NUMBER OF POINTS IN OUTPUT
N = length(a_in)*2 - 1;						


                                        % construction of a linear ramp
                                        % that will define vector angles
                                        % on the complex unit circle
linear_phase = [ (-2*pi/N)*[0:(N-1)/2]*( (N-1)/2 ) ];

                                        % optionally scale angles
phase_offset = (offset.*N);

                                        % construct a half of the complex impulse response by
                                        % converting amplitudes of magnitude response
                                        % to vectors with the same magnitude, but
                                        % with the complex phase angles defined above
half_wave = a_in.* exp(1i*(phase_offset+linear_phase) ); 

                                        % set the DC amplitude of the complex response
half_wave(1) = a_in(1);

                                        % compute the second half of the complex response
                                        % from the complex conjugate of the first half
                                        % (excluding dc value)
full_wave = [half_wave,conj(half_wave(end:-1:2))];

                                        % the impulse returned is the ifft of the 
                                        % complex response just constructed
a_out = real( ifft( full_wave ) );

return