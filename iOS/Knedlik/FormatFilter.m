//
//  FormatFilter.m
//  Fotopolis
//
//  Created by Martin Wenisch on 4/15/13.
//  Copyright (c) 2013 Martin Wenisch. All rights reserved.
//

#import "FormatFilter.h"


@interface FormatFilter ()

@property (nonatomic) CGFloat width;
@property (nonatomic) CGFloat height;

@end

@implementation FormatFilter

- (id)initWithCropRegion:(CGRect)newCropRegion
{
    self = [super initWithCropRegion:newCropRegion];
    if (self) {
        self.ratioX = 1.0;
        self.ratioY = 1.0;
        self.type = 0;
    }
    return self;
}

- (void)setWidth:(CGFloat)width height:(CGFloat)height
{
    self.width = width;
    self.height = height;
    self.orientation = [UIApplication sharedApplication].statusBarOrientation;
    
    CGFloat x = 0.0, y = 0.0, nw = 0.0, nh = 0.0;

    if (width / self.ratioX > height / self.ratioY) {
        x = (1 - (height / self.ratioY) / (width / self.ratioX)) / 2;
        y = 0;
        nw = (width - 2 * x * width) / width;
        nh = 1.0;
    } else {
        x = 0;
        y = (1 - (width / self.ratioX) / (height / self.ratioY)) / 2;
        nw = 1.0;
        nh = (height - 2 * y * height) / height;
    }
    
    if (self.type == 0) {  
        self.cropRegion = CGRectMake(x, y, nw, nh);
    } else {
        self.cropRegion = CGRectMake(self.ratioX, self.ratioX, self.cropWidth, self.cropHeight);
    }
}

- (void)rotationChanged
{
    if (self.orientation != [UIApplication sharedApplication].statusBarOrientation && !(UIInterfaceOrientationIsLandscape(self.orientation) && UIInterfaceOrientationIsLandscape([UIApplication sharedApplication].statusBarOrientation))) {

        CGFloat temp = self.ratioX;
        self.ratioX = self.ratioY;
        self.ratioY = temp;
        
        [self setWidth:self.height height:self.width];
    }
}

- (void)setFormatRatioX:(CGFloat)x ratioY:(CGFloat)y
{
    self.type = 0;
    self.ratioX = x;
    self.ratioY = y;
    [self setWidth:self.width height:self.height];
}

- (void)setFreeX:(CGFloat)x Y:(CGFloat)y width:(CGFloat)width height:(CGFloat)height
{
    self.type = 1;
    
}

@end
