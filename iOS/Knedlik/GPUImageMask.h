//
//  GPUImageMask.h
//  Happy
//
//  Created by Martin Wenisch on 10/9/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import "GPUImageTwoInputFilter.h"
#import <GPUImage.h>

@interface GPUImageMask : GPUImageTwoInputFilter

@property (nonatomic) int inverseMode;

- (void)generateTextureFromImage:(NSString *)img;
- (void)clearTexture;

@end
