//
//  FormatFilter.h
//  Fotopolis
//
//  Created by Martin Wenisch on 4/15/13.
//  Copyright (c) 2013 Martin Wenisch. All rights reserved.
//

#import <GPUImage.h>

@interface FormatFilter : GPUImageCropFilter

@property (nonatomic) CGFloat ratioX;
@property (nonatomic) CGFloat ratioY;
@property (nonatomic) CGFloat cropWidth;
@property (nonatomic) CGFloat cropHeight;
@property (nonatomic) UIInterfaceOrientation orientation;
@property (nonatomic) int type;

- (void)setWidth:(CGFloat)width height:(CGFloat)height;
- (void)rotationChanged;
- (void)setFormatRatioX:(CGFloat)x ratioY:(CGFloat)y;
- (void)setFreeX:(CGFloat)x Y:(CGFloat)y width:(CGFloat)width height:(CGFloat)height;


@end
