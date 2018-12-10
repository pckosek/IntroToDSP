function a_out = idft_fir( a_in, offset )

% pulse_response = idft_fir( spectrum_in, phase_offset )
%
% FUNCTION TO GENERATE A LTI IMPULSE RESPONSE FROM 
%       A MAGNITUDE RESPONSE (a_in)
%
%  OPTIONAL PARAMETER PHASE OFFSET (offset) defaults to zero.
%    offset can be a scalar or size(a_in) for designer phase 
%    variance


                                        % General theory of operation:
                                        %   - construct the magnitude response 
                                        %     in the frequency domain using data the
                                        %     user passed to the function
                                        %                                          
                                        %   - convert the complex magnitude response 
                                        %     back to a time domain impulse response
                                        %    
                                        %   - return


                                        % TOTAL NUMBER OF POINTS IN OUTPUT
N = length(a_in)*2 - 1;						


                                        % construction of a linear ramp
                                        % that will define vector angles
                                        % on the complex unit circle
linear_phase = [ (-2*pi/N)*[0:(N-1)/2]*( (N-1)/2 ) ];


                                        % optional nonlinear phase parameter
                                        % set to zero if not zpecified
if nargin<2
    offset = 0;
end
                                        % define the element-wise phase offset
                                        % (will be zero if not specified)
phase_offset = (offset.*N);

                                        % construct half of the complex (frequency domain) 
                                        % magnitude response by converting 
                                        % desired magnitude amplitudes (the data passed in)
                                        % to vectors with the same magnitude, but
                                        % with the complex phase angles defined above
half_wave = a_in.* exp(1i*(phase_offset+linear_phase) ); 

                                        % set the DC amplitude of the complex (frequency) response
half_wave(1) = a_in(1);

                                        % compute the second half of the frequency response
                                        % by taking the complex conjugate of the first half
                                        % (excluding dc value)
full_wave = [half_wave,conj(half_wave(end:-1:2))];

                                        % the impulse returned is just the ifft of the 
                                        % complex (frequency domain) response that we just constructed
a_out = real( ifft( full_wave ) );

return