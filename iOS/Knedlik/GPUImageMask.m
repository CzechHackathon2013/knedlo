//
//  GPUImageMask.m
//  Happy
//
//  Created by Martin Wenisch on 10/9/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import "GPUImageMask.h"

NSString *const kGPUImagePixelShaderStringImageMask = SHADER_STRING
(
 varying highp vec2 textureCoordinate;
 varying highp vec2 textureCoordinate2;
 
 uniform sampler2D inputImageTexture;
 uniform sampler2D inputImageTexture2;
 
 uniform int inverseMode;
 
 void main()
 {
	 highp vec4 base = texture2D(inputImageTexture2, textureCoordinate2);
     highp vec4 alpha;
     
     if (inverseMode == 0) {
         alpha = vec4(1.0, 1.0, 1.0, 1.0) - texture2D(inputImageTexture, textureCoordinate).aaaa;
     } else {
         alpha = texture2D(inputImageTexture, textureCoordinate).aaaa;
     }
     
     gl_FragColor = alpha*base;
     
 }
 );

@interface GPUImageMask ()

@property (nonatomic, strong) GPUImagePicture *secondTexture;

@property (nonatomic) GLint inverseModeUniform;

@end

@implementation GPUImageMask

- (id)init
{
    self = [self initWithFragmentShaderFromString:kGPUImagePixelShaderStringImageMask];
    if (self) {
        // [self generateTexture];
        self.inverseModeUniform = [filterProgram uniformIndex:@"inverseMode"];
        
        self.inverseMode = 0;
    }
    return self;
}

- (void)generateTextureFromImage:(NSString *)img
{
    UIImage *inputImage = [UIImage imageNamed:img];
    
    self.secondTexture = [[GPUImagePicture alloc] initWithImage:inputImage smoothlyScaleOutput:YES];
    [self.secondTexture processImage];
    [self.secondTexture addTarget:self];
}

- (void)clearTexture
{
    [self.secondTexture removeAllTargets];
    [self.secondTexture deleteOutputTexture];
    self.secondTexture = nil;
}

- (void)setInverseMode:(int)inverseMode
{
    _inverseMode = inverseMode;
    
    [self setInteger:self.inverseMode forUniform:self.inverseModeUniform program:filterProgram];
}


@end
